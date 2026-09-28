package com.sunny.basics.lesson03_rest;

/*
 * ============================================================
 *  LESSON 03: BUILDING A REST API (CRUD) - File 1 of 2: the data
 * ============================================================
 *
 *  A record (Java topic 33) is perfect for data that travels as JSON.
 *
 *  JSON <-> Java happens automatically (Jackson library):
 *    - Response: Spring turns  new Note(1, "Java", "Learn OOP")  into
 *                {"id":1,"title":"Java","content":"Learn OOP"}
 *    - Request:  Spring turns the JSON body of a POST back into a Note object.
 *  JSON field names = record component names.
 */
public record Note(Long id, String title, String content) {
}
