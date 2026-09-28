package com.sunny.basics.lesson05_validation;

import java.util.Map;

/*
 * ============================================================
 *  LESSON 05 - File 3 of 5: one consistent error format
 * ============================================================
 *
 *  Every error from our API will have this same JSON shape, so frontend
 *  developers always know what to expect:
 *
 *  {
 *    "status": 400,
 *    "error": "Bad Request",
 *    "message": "Validation failed",
 *    "details": { "email": "email must be a valid email address" }
 *  }
 */
public record ErrorResponse(int status, String error, String message, Map<String, String> details) {
}
