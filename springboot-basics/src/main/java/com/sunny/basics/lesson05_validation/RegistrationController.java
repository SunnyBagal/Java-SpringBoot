package com.sunny.basics.lesson05_validation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
 * ============================================================
 *  LESSON 05 - File 5 of 5: using @Valid and throwing exceptions
 * ============================================================
 *
 *  @Valid @RequestBody RegistrationRequest request
 *   -> Spring converts JSON to the record, THEN checks all its annotations.
 *   -> If any rule fails, this method is NOT called at all; Spring throws
 *      MethodArgumentNotValidException, which our GlobalExceptionHandler catches.
 *
 *  Notice how clean the methods are: no if-checks for bad input, no try-catch.
 */
@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    // Simple in-memory "table" of users: id -> name
    private final Map<Long, String> users = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // POST /api/users/register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> register(@Valid @RequestBody RegistrationRequest request) {
        if (users.containsValue(request.name())) {
            throw new IllegalArgumentException("user '" + request.name() + "' already exists");
        }
        long id = nextId.getAndIncrement();
        users.put(id, request.name());
        // Never send the password back!
        return Map.of("id", id, "name", request.name());
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable Long id) {
        String name = users.get(id);
        if (name == null) {
            throw new ResourceNotFoundException("User", id);  // -> 404 via GlobalExceptionHandler
        }
        return Map.of("id", id, "name", name);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * $ curl -X POST http://localhost:8080/api/users/register -H "Content-Type: application/json" \
 *        -d '{"name":"Sunny","email":"sunny@example.com","age":22,"password":"secret123"}'
 * {"id":1,"name":"Sunny"}                                         (status 201)
 *
 * $ curl -X POST http://localhost:8080/api/users/register -H "Content-Type: application/json" \
 *        -d '{"name":"","email":"not-an-email","age":15,"password":"123"}'
 * {"status":400,"error":"Bad Request","message":"Validation failed","details":{"age":"age must be at least 18","email":"email must be a valid email address","name":"name is required","password":"password must have at least 8 characters"}}
 *
 * $ curl -X POST http://localhost:8080/api/users/register -H "Content-Type: application/json" \
 *        -d '{"name":"Sunny","email":"s2@example.com","age":30,"password":"password1"}'
 * {"status":400,"error":"Bad Request","message":"user 'Sunny' already exists","details":{}}
 *
 * $ curl http://localhost:8080/api/users/1
 * {"id":1,"name":"Sunny"}
 *
 * $ curl http://localhost:8080/api/users/42
 * {"status":404,"error":"Not Found","message":"User with id 42 not found","details":{}}
 *
 *   (key order inside {"id":..,"name":..} may differ - Map.of doesn't keep order)
 * ----------------------------------------------------------
 */
