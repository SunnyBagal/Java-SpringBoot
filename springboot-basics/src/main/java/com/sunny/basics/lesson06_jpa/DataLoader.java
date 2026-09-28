package com.sunny.basics.lesson06_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * ============================================================
 *  LESSON 06 - File 6 of 6: sample data at startup
 * ============================================================
 *
 *  CommandLineRunner -> Spring calls run() ONCE, right after the app has started.
 *  Handy for loading demo data, printing info, one-time setup tasks.
 *
 *  LOGGING: Use a Logger instead of System.out.println in real apps.
 *   log.info / log.warn / log.error / log.debug -> each line gets a time, level
 *   and class name, and levels can be switched on/off in application.properties.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final StudentRepository repository;

    public DataLoader(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.saveAll(List.of(                     // saveAll = save many at once
                new Student("Asha Patil", "asha@example.com", 21, "Java"),
                new Student("Ravi Kumar", "ravi@example.com", 23, "Spring Boot"),
                new Student("Meera Joshi", "meera@example.com", 20, "Java")
        ));
        log.info("Loaded {} sample students", repository.count());   // {} is replaced by the value
    }
}

/*
 * ------------------------- OUTPUT (console) -------------------------
 * ... INFO ... c.sunny.basics.lesson06_jpa.DataLoader : Loaded 3 sample students
 * --------------------------------------------------------------------
 */
