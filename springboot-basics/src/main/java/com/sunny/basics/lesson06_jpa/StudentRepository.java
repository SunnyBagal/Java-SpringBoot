package com.sunny.basics.lesson06_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * ============================================================
 *  LESSON 06 - File 2 of 6: the REPOSITORY (database access)
 * ============================================================
 *
 *  This is just an INTERFACE - you write NO implementation!
 *  Spring Data creates a class for it at startup and makes it a bean.
 *
 *  JpaRepository<Student, Long>   ( <entity type, id type> )
 *  gives you these methods for free:
 *     save(student)        INSERT or UPDATE
 *     findById(id)         SELECT ... WHERE id = ?     (returns Optional)
 *     findAll()            SELECT * FROM students
 *     existsById(id)
 *     deleteById(id)       DELETE ... WHERE id = ?
 *     count()
 *
 *  DERIVED QUERIES: Spring reads the METHOD NAME and writes the SQL for you!
 *     findByCourse(c)                -> WHERE course = ?
 *     findByAgeGreaterThan(a)        -> WHERE age > ?
 *     findByNameContainingIgnoreCase -> WHERE lower(name) LIKE lower('%x%')
 *     existsByEmail(e)               -> SELECT count(*) > 0 WHERE email = ?
 *
 *  @Query: write the query yourself in JPQL (SQL-like, but uses CLASS and FIELD
 *          names instead of table and column names).
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByCourse(String course);

    List<Student> findByAgeGreaterThan(int age);

    List<Student> findByNameContainingIgnoreCase(String part);

    boolean existsByEmail(String email);

    @Query("SELECT AVG(s.age) FROM Student s")
    Double averageAge();
}
