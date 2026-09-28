package com.sunny.basics;   // package = folder path; groups related classes (com/sunny/basics)

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * ============================================================
 *  THE MAIN CLASS - where every Spring Boot app starts
 * ============================================================
 *
 *  @SpringBootApplication is 3 annotations in one:
 *
 *   1) @Configuration       -> this class can define beans (objects Spring manages)
 *   2) @EnableAutoConfiguration
 *                           -> Spring Boot looks at the libraries in pom.xml and sets
 *                              things up for you. Sees the web starter? Starts Tomcat on
 *                              port 8080. Sees H2 + JPA? Creates a database connection.
 *                              This is the "magic" that makes Spring Boot so quick.
 *   3) @ComponentScan       -> scan THIS package and all sub-packages
 *                              (com.sunny.basics.*) for classes marked @Component,
 *                              @Service, @RestController, @Repository... and create
 *                              objects of them automatically.
 *
 *  IMPORTANT: Every lesson package (lesson01_hello, lesson02_di...) is INSIDE
 *  com.sunny.basics, so Spring finds them all. A class outside this package
 *  would be ignored!
 *
 *  RUN (from the springboot-basics folder):
 *      ./mvnw spring-boot:run
 *  Then open http://localhost:8080/hello in your browser. Stop with Ctrl + C.
 */
@SpringBootApplication
public class SpringbootBasicsApplication {

	public static void main(String[] args) {
		// Starts Spring: creates the "application context" (a container holding all
		// your beans), wires them together, and starts the embedded web server.
		SpringApplication.run(SpringbootBasicsApplication.class, args);
	}

}

/*
 * ------------------- OUTPUT (console, shortened) -------------------
 *   .   ____          _            __ _ _
 *  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
 * ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 *  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
 *   '  |____| .__|_| |_|_| |_\__, | / / / /
 *  =========|_|==============|___/=/_/_/_/
 *
 *  :: Spring Boot ::                (v4.1.1)
 *
 *  ... Starting SpringbootBasicsApplication using Java 21 ...
 *  ... Tomcat started on port 8080 (http) with context path '/'
 *  ... Started SpringbootBasicsApplication in 2.8 seconds  (time varies)
 * -------------------------------------------------------------------
 */
