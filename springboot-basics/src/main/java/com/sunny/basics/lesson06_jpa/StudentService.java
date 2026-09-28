package com.sunny.basics.lesson06_jpa;

import com.sunny.basics.lesson05_validation.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * ============================================================
 *  LESSON 06 - File 4 of 6: the SERVICE (business logic)
 * ============================================================
 *
 *  LAYERED ARCHITECTURE - every real Spring Boot app is organised like this:
 *
 *     HTTP request
 *         |
 *     [ Controller ]  -> web stuff only: URLs, JSON, status codes
 *         |
 *     [  Service   ]  -> business rules: "email must be unique", calculations
 *         |
 *     [ Repository ]  -> database access only
 *         |
 *     [  Database  ]
 *
 *  Each layer only talks to the one below it. This keeps code organised and
 *  easy to test (you can test the service with a fake repository).
 *
 *  @Transactional -> all database work inside the method happens as ONE unit:
 *   if anything fails halfway, everything is rolled back (undone).
 *   readOnly = true is a small optimisation for methods that only read.
 */
@Service
@Transactional(readOnly = true)          // default for every method in this class
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {   // constructor injection (lesson 02)
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        // findById returns Optional<Student>; orElseThrow gives the student or throws
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));   // -> 404
    }

    public List<Student> findByCourse(String course) {
        return repository.findByCourse(course);
    }

    public List<Student> search(String namePart) {
        return repository.findByNameContainingIgnoreCase(namePart);
    }

    public List<Student> olderThan(int age) {
        return repository.findByAgeGreaterThan(age);
    }

    public double averageAge() {
        Double avg = repository.averageAge();
        return avg == null ? 0 : avg;                   // AVG of an empty table is null
    }

    @Transactional                                      // writes need a normal (read-write) transaction
    public Student create(StudentRequest request) {
        if (repository.existsByEmail(request.email())) {
            // business rule -> 400 via GlobalExceptionHandler (lesson 05)
            throw new IllegalArgumentException("email " + request.email() + " is already registered");
        }
        Student student = new Student(request.name(), request.email(), request.age(), request.course());
        return repository.save(student);                // INSERT; the returned object has the new id
    }

    @Transactional
    public Student update(Long id, StudentRequest request) {
        Student student = findById(id);                 // throws 404 if missing
        student.setName(request.name());
        student.setEmail(request.email());
        student.setAge(request.age());
        student.setCourse(request.course());
        return repository.save(student);                // UPDATE (the entity already has an id)
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student", id);
        }
        repository.deleteById(id);
    }
}
