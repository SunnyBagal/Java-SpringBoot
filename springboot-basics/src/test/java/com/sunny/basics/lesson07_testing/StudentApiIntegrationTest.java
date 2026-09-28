package com.sunny.basics.lesson07_testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * ============================================================
 *  LESSON 07 - File 3 of 3: INTEGRATION test (the whole app)
 * ============================================================
 *
 *  @SpringBootTest -> starts the FULL application: every bean, the H2 database,
 *                     the DataLoader... everything, just like ./mvnw spring-boot:run.
 *  @AutoConfigureMockMvc -> gives us a MockMvc to send requests into it.
 *
 *  Integration tests are slower than unit tests but prove that all the layers
 *  (Controller -> Service -> Repository -> DB -> ExceptionHandler) work TOGETHER.
 *
 *  A good test suite has MANY small unit tests and FEWER integration tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
class StudentApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsSampleStudentsLoadedAtStartup() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))           // DataLoader inserted 3
                .andExpect(jsonPath("$[0].name").value("Asha Patil")); // $[0] = first array item
    }

    @Test
    void filtersByCourse() throws Exception {
        mockMvc.perform(get("/api/students").param("course", "Spring Boot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Ravi Kumar"));
    }

    @Test
    void missingStudentGives404WithErrorBody() throws Exception {
        mockMvc.perform(get("/api/students/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Student with id 999 not found"));
    }

    @Test
    void invalidStudentGives400WithFieldErrors() throws Exception {
        String badJson = """
                {"name": "", "email": "nope", "age": 10, "course": "Java"}
                """;                                  // text block (Java topic 07)

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").value("name is required"))
                .andExpect(jsonPath("$.details.email").value("email must be valid"))
                .andExpect(jsonPath("$.details.age").value("age must be at least 16"));
    }
}

/*
 * ------------------------- OUTPUT (./mvnw test, shortened) -------------------------
 * [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: ... s -- in com.sunny.basics.lesson07_testing.StudentApiIntegrationTest
 * -----------------------------------------------------------------------------------
 */
