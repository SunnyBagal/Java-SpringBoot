package com.sunny.basics.lesson05_validation;

/*
 * ============================================================
 *  LESSON 05 - File 2 of 5: a custom exception
 * ============================================================
 *
 *  Thrown when something the client asked for doesn't exist.
 *  It extends RuntimeException (UNCHECKED - Java topic 26) so methods don't
 *  need "throws" everywhere.
 *
 *  We just THROW it from anywhere (controller or service). The
 *  GlobalExceptionHandler (file 3) turns it into a clean 404 response.
 *  Lesson 06 reuses it for students.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " with id " + id + " not found");
    }
}
