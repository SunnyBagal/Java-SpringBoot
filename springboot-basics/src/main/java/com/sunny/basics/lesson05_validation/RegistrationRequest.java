package com.sunny.basics.lesson05_validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * ============================================================
 *  LESSON 05: VALIDATION & EXCEPTION HANDLING
 *  File 1 of 5 - rules on the incoming data
 * ============================================================
 *
 *  NEVER trust data sent by a client. Bean Validation lets you declare rules
 *  as annotations instead of writing lots of if-statements.
 *
 *  COMMON ANNOTATIONS (package jakarta.validation.constraints):
 *     @NotNull          value must not be null
 *     @NotBlank         String not null AND not just spaces
 *     @Size(min, max)   length of a String / size of a list
 *     @Min / @Max       number limits
 *     @Email            looks like an email address
 *     @Pattern(regexp)  must match a regular expression
 *     @Positive, @Past, @Future ...
 *
 *  The rules only run when the controller parameter is marked @Valid (file 4).
 *  The "message" is what the client sees when the rule fails.
 */
public record RegistrationRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid email address")
        String email,

        @Min(value = 18, message = "age must be at least 18")
        @Max(value = 100, message = "age must be at most 100")
        int age,

        @Size(min = 8, message = "password must have at least 8 characters")
        String password
) {
}
