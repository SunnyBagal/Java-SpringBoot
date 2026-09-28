package com.sunny.basics.lesson05_validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.TreeMap;

/*
 * ============================================================
 *  LESSON 05 - File 4 of 5: handle exceptions in ONE place
 * ============================================================
 *
 *  @RestControllerAdvice -> this class watches ALL controllers in the app.
 *  @ExceptionHandler(X.class) -> "whenever any controller throws X, run this
 *                                method and send ITS return value instead".
 *
 *  Without this, a thrown exception gives the client an ugly 500 error or a
 *  generic message. With it, controllers stay clean (just throw) and all error
 *  responses look the same.
 *
 *     Controller throws ResourceNotFoundException
 *            |
 *            v
 *     GlobalExceptionHandler.handleNotFound()  ->  404 + ErrorResponse JSON
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - something wasn't found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), Map.of());
    }

    // 400 - @Valid failed. Spring throws MethodArgumentNotValidException for us.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new TreeMap<>();     // TreeMap -> fields sorted A-Z
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
    }

    // 400 - our own business-rule checks (e.g. "email already registered")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), Map.of());
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message, Map<String, String> details) {
        ErrorResponse body = new ErrorResponse(status.value(), status.getReasonPhrase(), message, details);
        return ResponseEntity.status(status).body(body);
    }
}
