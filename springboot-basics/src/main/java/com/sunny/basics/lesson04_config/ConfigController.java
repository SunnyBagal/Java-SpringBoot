package com.sunny.basics.lesson04_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * ============================================================
 *  LESSON 04 - File 3 of 3: reading config values
 * ============================================================
 *
 *  TWO WAYS to read settings:
 *
 *  1) @Value("${key}") - inject ONE value. Quick and simple.
 *       @Value("${app.owner}") String owner
 *     With a default if the key is missing:
 *       @Value("${app.timezone:Asia/Kolkata}")   <- text after ":" is the default
 *
 *  2) @ConfigurationProperties record (AppProperties) - inject a whole GROUP.
 *     Type-safe and tidy; preferred when you have several related settings.
 *
 *  OVERRIDING without touching the file (highest priority wins):
 *     command line:   ./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.owner=Ravi
 *     env variable:   APP_OWNER=Ravi ./mvnw spring-boot:run
 *
 *  PROFILES: application-dev.properties / application-prod.properties hold
 *  environment-specific values; activate with spring.profiles.active=dev
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final String owner;
    private final String timezone;
    private final AppProperties props;
    private final DateTimeFormatter dateFormatter;

    public ConfigController(@Value("${app.owner}") String owner,
                            @Value("${app.timezone:Asia/Kolkata}") String timezone,   // key not in file -> default used
                            AppProperties props,                  // the @ConfigurationProperties bean
                            DateTimeFormatter dateFormatter) {    // the @Bean from AppConfig
        this.owner = owner;
        this.timezone = timezone;
        this.props = props;
        this.dateFormatter = dateFormatter;
    }

    // GET /config/value -> values injected with @Value
    @GetMapping("/value")
    public Map<String, String> fromValue() {
        Map<String, String> result = new LinkedHashMap<>();   // LinkedHashMap keeps key order in JSON
        result.put("owner", owner);
        result.put("timezone", timezone);
        return result;
    }

    // GET /config/properties -> the whole AppProperties record as JSON
    @GetMapping("/properties")
    public AppProperties fromProperties() {
        return props;
    }

    // GET /config/date -> uses the DateTimeFormatter bean
    @GetMapping("/date")
    public String date() {
        return "Formatted with our @Bean: " + LocalDate.of(2026, 1, 26).format(dateFormatter);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * $ curl http://localhost:8080/config/value
 * {"owner":"Sunny","timezone":"Asia/Kolkata"}
 *
 * $ curl http://localhost:8080/config/properties
 * {"welcomeMessage":"Welcome to Spring Boot basics!","owner":"Sunny","maxNotes":5,"features":{"darkMode":true,"beta":false}}
 *
 * $ curl http://localhost:8080/config/date
 * Formatted with our @Bean: 26-01-2026
 * ----------------------------------------------------------
 */
