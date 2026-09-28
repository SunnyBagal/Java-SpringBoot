package com.sunny.basics.lesson06_jpa;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * ============================================================
 *  LESSON 06: DATABASE WITH SPRING DATA JPA
 *  File 1 of 6 - the ENTITY (a Java class = a database table)
 * ============================================================
 *
 *  JPA (Jakarta Persistence API) maps Java objects <-> database rows.
 *  Hibernate is the library that does the actual work (it writes the SQL).
 *
 *     Java class Student         <->   table "students"
 *     field  name                <->   column "name"
 *     one Student object         <->   one row
 *
 *  ANNOTATIONS:
 *   @Entity          -> this class is stored in the database
 *   @Table(name=..)  -> table name (default would be "student")
 *   @Id              -> the PRIMARY KEY (unique id for each row)
 *   @GeneratedValue  -> the database generates the id (1, 2, 3...) automatically
 *   @Column(...)     -> column options: nullable, unique, length...
 *
 *  With spring.jpa.hibernate.ddl-auto=create-drop, Hibernate CREATES this table
 *  at startup. Watch the console: you'll see "create table students (...)".
 *
 *  RULES FOR ENTITIES:
 *   - Must have a no-argument constructor (JPA uses it to build objects from rows).
 *   - Use a normal class with getters/setters (not a record - entities are mutable).
 *
 *  @JsonPropertyOrder -> not JPA; it's a Jackson (JSON) annotation that fixes the
 *   order of fields in the JSON response, so "id" comes first.
 */
@JsonPropertyOrder({"id", "name", "email", "age", "course"})
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto-increment id
    private Long id;

    @Column(nullable = false)                             // NOT NULL in the database
    private String name;

    @Column(nullable = false, unique = true)              // no two students with the same email
    private String email;

    private int age;

    private String course;

    protected Student() {
        // required by JPA; "protected" so our own code uses the constructor below
    }

    public Student(String name, String email, int age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    // Getters are used by Jackson to build the JSON response
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
}
