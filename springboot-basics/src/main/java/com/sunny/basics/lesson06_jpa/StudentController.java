package com.sunny.basics.lesson06_jpa;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
 * ============================================================
 *  LESSON 06 - File 5 of 6: the CONTROLLER (web layer)
 * ============================================================
 *
 *  Thin controller: it only maps URLs to service calls. No business logic,
 *  no database code. Everything we learned comes together here:
 *   - @RestController + mappings            (lesson 01, 03)
 *   - constructor injection                 (lesson 02)
 *   - @Valid + GlobalExceptionHandler       (lesson 05)
 *   - Service -> Repository -> Database     (this lesson)
 *
 *   GET    /api/students                  all students
 *   GET    /api/students/1                one student (404 if missing)
 *   GET    /api/students?course=Java      filter by course
 *   GET    /api/students/search?name=ra   name contains "ra"
 *   GET    /api/students/older-than/21    age > 21
 *   GET    /api/students/stats            count + average age
 *   POST   /api/students                  create  (201)
 *   PUT    /api/students/1                update
 *   DELETE /api/students/1                delete  (204)
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // "required = false" -> ?course=... is optional; without it we return everyone
    @GetMapping
    public List<Student> getAll(@RequestParam(required = false) String course) {
        return course == null ? service.findAll() : service.findByCourse(course);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return service.search(name);
    }

    @GetMapping("/older-than/{age}")
    public List<Student> olderThan(@PathVariable int age) {
        return service.olderThan(age);
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return Map.of("count", service.findAll().size(), "averageAge", service.averageAge());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody StudentRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 *  (DataLoader inserts 3 students at startup - see file 6)
 *
 * $ curl http://localhost:8080/api/students
 * [{"id":1,"name":"Asha Patil","email":"asha@example.com","age":21,"course":"Java"},{"id":2,"name":"Ravi Kumar","email":"ravi@example.com","age":23,"course":"Spring Boot"},{"id":3,"name":"Meera Joshi","email":"meera@example.com","age":20,"course":"Java"}]
 *
 * $ curl http://localhost:8080/api/students/2
 * {"id":2,"name":"Ravi Kumar","email":"ravi@example.com","age":23,"course":"Spring Boot"}
 *
 * $ curl "http://localhost:8080/api/students?course=Java"
 * [{"id":1,"name":"Asha Patil","email":"asha@example.com","age":21,"course":"Java"},{"id":3,"name":"Meera Joshi","email":"meera@example.com","age":20,"course":"Java"}]
 *
 * $ curl "http://localhost:8080/api/students/search?name=ra"
 * [{"id":2,"name":"Ravi Kumar","email":"ravi@example.com","age":23,"course":"Spring Boot"},{"id":3,"name":"Meera Joshi","email":"meera@example.com","age":20,"course":"Java"}]
 *   (Mee-RA also contains "ra" - IgnoreCase matches both)
 *
 * $ curl http://localhost:8080/api/students/older-than/20
 * [{"id":1,"name":"Asha Patil","email":"asha@example.com","age":21,"course":"Java"},{"id":2,"name":"Ravi Kumar","email":"ravi@example.com","age":23,"course":"Spring Boot"}]
 *
 * $ curl http://localhost:8080/api/students/stats
 * {"count":3,"averageAge":21.333333333333332}
 *
 * $ curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" \
 *        -d '{"name":"Sunny Bagal","email":"sunny@example.com","age":22,"course":"Spring Boot"}'
 * {"id":4,"name":"Sunny Bagal","email":"sunny@example.com","age":22,"course":"Spring Boot"}
 *
 * $ curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" \
 *        -d '{"name":"Copy","email":"sunny@example.com","age":22,"course":"Java"}'
 * {"status":400,"error":"Bad Request","message":"email sunny@example.com is already registered","details":{}}
 *
 * $ curl -X PUT http://localhost:8080/api/students/4 -H "Content-Type: application/json" \
 *        -d '{"name":"Sunny Bagal","email":"sunny@example.com","age":23,"course":"Java"}'
 * {"id":4,"name":"Sunny Bagal","email":"sunny@example.com","age":23,"course":"Java"}
 *
 * $ curl -i -X DELETE http://localhost:8080/api/students/4
 * HTTP/1.1 204
 *
 * $ curl http://localhost:8080/api/students/4
 * {"status":404,"error":"Not Found","message":"Student with id 4 not found","details":{}}
 *
 *  In the console you'll also see the SQL Hibernate runs, e.g.:
 *  Hibernate: insert into students (age,course,email,name,id) values (?,?,?,?,default)
 *  Hibernate: select s1_0.id,s1_0.age,s1_0.course,s1_0.email,s1_0.name from students s1_0
 * ----------------------------------------------------------
 */
