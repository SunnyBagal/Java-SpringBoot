package com.sunny.basics.lesson04_config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/*
 * ============================================================
 *  LESSON 04 - File 2 of 3: @Configuration and @Bean
 * ============================================================
 *
 *  @Configuration -> a class whose job is to set up beans.
 *
 *  @Bean -> the method's RETURN VALUE becomes a bean managed by Spring.
 *   Use it when you can't put @Component on a class yourself, e.g. classes from
 *   the JDK or another library (like DateTimeFormatter below). The bean name is
 *   the method name ("dateFormatter").
 *
 *  @EnableConfigurationProperties(AppProperties.class)
 *   -> create the AppProperties bean and fill it from application.properties.
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

    @Bean
    public DateTimeFormatter dateFormatter() {
        // Now any class can ask for a DateTimeFormatter in its constructor
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
}
