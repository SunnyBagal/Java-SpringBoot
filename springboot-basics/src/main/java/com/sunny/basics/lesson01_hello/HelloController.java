package com.sunny.basics.lesson01_hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * ============================================================
 *  LESSON 01: YOUR FIRST REST ENDPOINT
 * ============================================================
 *
 *  WHAT IS A REST API?
 *   A program that answers HTTP requests (like a website), but it returns DATA
 *   (usually JSON) instead of web pages. Mobile apps and frontends call it.
 *
 *     Browser / Postman / curl  --- GET /hello --->  Spring Boot app
 *                               <--- "Hello..." ---
 *
 *  HTTP METHODS (verbs):
 *     GET    -> read data           POST   -> create data
 *     PUT    -> update/replace      DELETE -> delete data
 *
 *  ANNOTATIONS USED HERE:
 *   @RestController -> "this class handles web requests, and whatever my methods
 *                      return should be sent back as the response body".
 *                      Spring creates ONE object of this class for you at startup.
 *   @GetMapping("/path") -> "call this method when a GET request hits /path".
 *   @RequestParam   -> read a value from the URL query string: /greet?name=Sunny
 *
 *  JSON: If a method returns an object/Map/List, Spring automatically converts it
 *        to JSON using the Jackson library. You don't write any JSON code!
 *
 *  TRY IT (app running with ./mvnw spring-boot:run):
 *      open http://localhost:8080/hello in a browser, or use curl in a terminal.
 */
@RestController
public class HelloController {

    // GET http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";            // plain text response
    }

    // GET http://localhost:8080/greet?name=Sunny
    // defaultValue -> the parameter becomes optional; "Guest" is used when it's missing
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "Guest") String name) {
        return "Hello, " + name + "!";
    }

    // GET http://localhost:8080/info  -> returns JSON
    @GetMapping("/info")
    public Map<String, Object> info() {
        // Map.of creates an unmodifiable map; Spring turns it into a JSON object
        return Map.of(
                "app", "springboot-basics",
                "lesson", 1,
                "topic", "Hello World REST API"
        );
    }

    // GET http://localhost:8080/add?a=5&b=7
    // Spring converts the text "5" and "7" to int automatically
    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * $ curl http://localhost:8080/hello
 * Hello, Spring Boot!
 *
 * $ curl "http://localhost:8080/greet?name=Sunny"
 * Hello, Sunny!
 *
 * $ curl http://localhost:8080/greet
 * Hello, Guest!
 *
 * $ curl http://localhost:8080/info
 * {"app":"springboot-basics","lesson":1,"topic":"Hello World REST API"}
 *   (the order of keys may differ - Map.of doesn't keep order)
 *
 * $ curl "http://localhost:8080/add?a=5&b=7"
 * 12
 * ----------------------------------------------------------
 */
