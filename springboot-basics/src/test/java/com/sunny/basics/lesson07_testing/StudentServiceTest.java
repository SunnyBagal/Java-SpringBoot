package com.sunny.basics.lesson07_testing;

import com.sunny.basics.lesson05_validation.ResourceNotFoundException;
import com.sunny.basics.lesson06_jpa.Student;
import com.sunny.basics.lesson06_jpa.StudentRepository;
import com.sunny.basics.lesson06_jpa.StudentRequest;
import com.sunny.basics.lesson06_jpa.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * ============================================================
 *  LESSON 07 - File 2 of 3: UNIT test with Mockito (no Spring at all)
 * ============================================================
 *
 *  A UNIT test checks ONE class on its own. The service normally needs a real
 *  repository + database... but we give it a FAKE ("mock") repository instead.
 *
 *  Mockito:
 *   @Mock                       -> create a fake StudentRepository
 *   when(x).thenReturn(y)       -> "when the service calls x, pretend the answer is y"
 *   verify(mock).method(...)    -> check that a method WAS called
 *   verify(mock, never())...    -> check that a method was NOT called
 *
 *  This is only easy because StudentService uses CONSTRUCTOR INJECTION
 *  (lesson 02): we just call  new StudentService(fakeRepository).
 *
 *  AssertJ: assertThat(actual).isEqualTo(expected) - readable assertions.
 *
 *  Pattern used in each test: ARRANGE (set up) -> ACT (call) -> ASSERT (check)
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    private StudentService service;

    @BeforeEach                                   // runs before EACH test -> fresh service every time
    void setUp() {
        service = new StudentService(repository);
    }

    @Test
    void findByIdReturnsStudentWhenPresent() {
        // ARRANGE
        Student asha = new Student("Asha", "asha@example.com", 21, "Java");
        when(repository.findById(1L)).thenReturn(Optional.of(asha));

        // ACT
        Student result = service.findById(1L);

        // ASSERT
        assertThat(result.getName()).isEqualTo("Asha");
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Student with id 99 not found");
    }

    @Test
    void createRejectsDuplicateEmail() {
        when(repository.existsByEmail("asha@example.com")).thenReturn(true);
        StudentRequest request = new StudentRequest("Asha 2", "asha@example.com", 22, "Java");

        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already registered");

        verify(repository, never()).save(any());  // nothing should have been saved
    }

    @Test
    void createSavesNewStudent() {
        when(repository.existsByEmail("new@example.com")).thenReturn(false);
        // "save" returns whatever it was given (like a real repository would)
        when(repository.save(any(Student.class))).thenAnswer(call -> call.getArgument(0));

        Student saved = service.create(new StudentRequest("New", "new@example.com", 19, "Spring Boot"));

        assertThat(saved.getEmail()).isEqualTo("new@example.com");
        assertThat(saved.getCourse()).isEqualTo("Spring Boot");
        verify(repository).save(any(Student.class));
    }
}

/*
 * ------------------------- OUTPUT (./mvnw test, shortened) -------------------------
 * [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: ... s -- in com.sunny.basics.lesson07_testing.StudentServiceTest
 * -----------------------------------------------------------------------------------
 */
