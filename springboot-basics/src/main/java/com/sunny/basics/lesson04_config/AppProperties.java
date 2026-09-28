package com.sunny.basics.lesson04_config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * ============================================================
 *  LESSON 04: CONFIGURATION - File 1 of 3: type-safe settings
 * ============================================================
 *
 *  WHY: Values like URLs, limits, messages, passwords shouldn't be hard-coded
 *       in Java. Put them in src/main/resources/application.properties and
 *       change them without recompiling (or per environment: dev / prod).
 *
 *  @ConfigurationProperties(prefix = "app")
 *   Binds EVERY property that starts with "app." to this record:
 *
 *     application.properties              ->  AppProperties
 *     ----------------------------------      --------------------------
 *     app.welcome-message=Welcome...      ->  welcomeMessage
 *     app.owner=Sunny                     ->  owner
 *     app.max-notes=5                     ->  maxNotes      (text "5" -> int 5)
 *     app.features.dark-mode=true         ->  features.darkMode
 *     app.features.beta=false             ->  features.beta
 *
 *  "Relaxed binding": welcome-message, welcomeMessage and WELCOME_MESSAGE all match.
 *
 *  It becomes a bean because AppConfig (file 2) enables it.
 */
@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String welcomeMessage,
        String owner,
        int maxNotes,
        Features features       // nested group: app.features.*
) {
    public record Features(boolean darkMode, boolean beta) {
    }
}
