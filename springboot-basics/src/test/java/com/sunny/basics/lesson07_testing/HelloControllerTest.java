package com.sunny.basics.lesson07_testing;

import com.sunny.basics.lesson01_hello.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * ============================================================
 *  LESSON 07: TESTING - File 1 of 3: testing a controller (web layer only)
 * ============================================================
 *
 *  WHY TEST? Tests are code that checks your code. Run them after every change
 *  and you'll know immediately if you broke something.
 *
 *  JUnit 5 basics:
 *   @Test        -> this method is a test
 *   assertions   -> check the result; if a check fails, the test fails
 *
 *  @WebMvcTest(HelloController.class)
 *   -> starts ONLY the web layer with this one controller (fast!), no database.
 *
 *  MockMvc -> sends fake HTTP requests to your controllers without a real
 *             server or network, and lets you check the response.
 *
 *  In tests, field injection with @Autowired is fine and common.
 *
 *  RUN ALL TESTS:  ./mvnw test
 */
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello"))                        // send GET /hello
                .andExpect(status().isOk())                   // expect 200
                .andExpect(content().string("Hello, Spring Boot!"));   // expect this body
    }

    @Test
    void greetUsesDefaultNameWhenMissing() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Guest!"));
    }

    @Test
    void addSumsTwoNumbers() throws Exception {
        mockMvc.perform(get("/add").param("a", "5").param("b", "7"))   // ?a=5&b=7
                .andExpect(status().isOk())
                .andExpect(content().string("12"));
    }

    @Test
    void addWithMissingParamIsBadRequest() throws Exception {
        mockMvc.perform(get("/add").param("a", "5"))          // b is missing
                .andExpect(status().isBadRequest());          // Spring answers 400 for us
    }

    @Test
    void infoReturnsJson() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lesson").value(1))     // $ = the JSON root object
                .andExpect(jsonPath("$.app").value("springboot-basics"));
    }
}

/*
 * ------------------------- OUTPUT (./mvnw test, shortened) -------------------------
 * [INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: ... s -- in com.sunny.basics.lesson07_testing.HelloControllerTest
 * -----------------------------------------------------------------------------------
 */
