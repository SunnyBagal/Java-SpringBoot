package com.sunny.basics.lesson06_jpa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/*
 * ============================================================
 *  LESSON 06 - File 3 of 6: a DTO for incoming data
 * ============================================================
 *
 *  DTO = Data Transfer Object: the shape of data the CLIENT sends.
 *
 *  WHY not accept the Student entity directly?
 *   - The client shouldn't be able to set fields like "id".
 *   - Validation rules for the API stay separate from database mapping.
 *   - You can change the database without breaking the API (and vice-versa).
 *
 *  Uses the validation annotations from lesson 05.
 */
public record StudentRequest(
        @NotBlank(message = "name is required") String name,
        @NotBlank(message = "email is required") @Email(message = "email must be valid") String email,
        @Min(value = 16, message = "age must be at least 16") int age,
        @NotBlank(message = "course is required") String course
) {
}
