# Spring Boot Basics

A single, runnable Spring Boot app where each **lesson is a package**. Every file has
comments explaining what each line does, and the controllers end with the **exact
`curl` requests and responses** (checked by running the app).

> **Before starting:** finish [`java-basics/`](../java-basics/) at least up to topic 25
> (interfaces). Spring Boot is built on classes, interfaces, constructors and annotations.

## What is Spring Boot?

- **Spring** is a big Java framework for building backend applications. Its core idea is
  **Dependency Injection**: you write classes, and Spring creates them and connects them.
- **Spring Boot** is Spring with sensible defaults. It needs almost no setup, has an embedded
  web server (Tomcat), and uses "starters" so one dependency brings in a whole feature.

```
Client (browser / mobile app / curl)
        |  HTTP request (GET /api/students)
        v
+--------------------------------------------------+
|  Spring Boot app (embedded Tomcat on port 8080)  |
|                                                  |
|   @RestController   -> handles URLs, JSON        |
|        |                                         |
|   @Service          -> business logic            |
|        |                                         |
|   @Repository       -> database access           |
+--------|-----------------------------------------+
         v
     Database (H2 here; MySQL/PostgreSQL in real apps)
```

## How to run

You need JDK 17+ (this project uses 21). You do **not** need to install Maven, because the
`mvnw` wrapper downloads it for you.

```bash
cd springboot-basics

./mvnw spring-boot:run      # start the app  -> http://localhost:8080/hello
                            # stop it with Ctrl + C
./mvnw test                 # run all tests (lesson 07)
```

Then try any `curl` command from the OUTPUT block at the bottom of each controller.
[Postman](https://www.postman.com/) also works if you prefer a GUI.

H2 database console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:studentdb`, user `sa`, empty password)

## Lessons

All code is in `src/main/java/com/sunny/basics/`.

| # | Package | What you learn | Try |
|---|---------|----------------|-----|
| 00 | [`SpringbootBasicsApplication.java`](src/main/java/com/sunny/basics/SpringbootBasicsApplication.java) | `@SpringBootApplication`, auto-configuration, component scanning | start the app |
| 01 | [`lesson01_hello`](src/main/java/com/sunny/basics/lesson01_hello/) | `@RestController`, `@GetMapping`, `@RequestParam`, JSON responses | `GET /hello` |
| 02 | [`lesson02_di`](src/main/java/com/sunny/basics/lesson02_di/) | Beans, IoC, constructor injection, `@Service`, `@Primary`, `@Qualifier`, singletons | `GET /di/greet/Sunny` |
| 03 | [`lesson03_rest`](src/main/java/com/sunny/basics/lesson03_rest/) | Full CRUD, `@PostMapping`/`@PutMapping`/`@DeleteMapping`, `@RequestBody`, `@PathVariable`, `ResponseEntity`, status codes | `POST /api/notes` |
| 04 | [`lesson04_config`](src/main/java/com/sunny/basics/lesson04_config/) | `application.properties`, `@Value`, `@ConfigurationProperties`, `@Configuration` + `@Bean` | `GET /config/properties` |
| 05 | [`lesson05_validation`](src/main/java/com/sunny/basics/lesson05_validation/) | `@Valid`, `@NotBlank`/`@Email`/`@Min`, custom exceptions, `@RestControllerAdvice` | `POST /api/users/register` |
| 06 | [`lesson06_jpa`](src/main/java/com/sunny/basics/lesson06_jpa/) | `@Entity`, `JpaRepository`, derived queries, `@Query`, Controller→Service→Repository layers, DTOs, `@Transactional`, `CommandLineRunner`, logging | `GET /api/students` |
| 07 | [`lesson07_testing`](src/test/java/com/sunny/basics/lesson07_testing/) (in `src/test`) | JUnit 5, `@WebMvcTest` + MockMvc, Mockito unit tests, `@SpringBootTest` integration tests | `./mvnw test` |

## Annotation cheat sheet

| Annotation | Meaning |
|------------|---------|
| `@SpringBootApplication` | Main class: enables auto-config + component scanning |
| `@Component` / `@Service` / `@Repository` | "Spring, create and manage an object of this class" (a bean) |
| `@RestController` | Bean that handles HTTP requests and returns data (JSON) |
| `@RequestMapping("/x")` | URL prefix for all methods in a controller |
| `@GetMapping` `@PostMapping` `@PutMapping` `@DeleteMapping` | Map an HTTP method + URL to a Java method |
| `@PathVariable` | Value from the URL path: `/students/{id}` |
| `@RequestParam` | Value from the query string: `?course=Java` |
| `@RequestBody` | Convert request JSON into a Java object |
| `@ResponseStatus` | Set the HTTP status code of a response |
| `@Valid` | Run the validation annotations on this object |
| `@Primary` / `@Qualifier` | Choose between several beans of the same type |
| `@Configuration` + `@Bean` | Create beans with a method (e.g. for library classes) |
| `@Value("${key}")` | Inject one value from `application.properties` |
| `@ConfigurationProperties` | Bind a group of properties to a class or record |
| `@RestControllerAdvice` + `@ExceptionHandler` | Turn exceptions into HTTP error responses, in one place |
| `@Entity` `@Id` `@GeneratedValue` `@Column` | Map a class to a database table |
| `@Transactional` | Run database work as one all-or-nothing unit |

## Project layout (standard Maven structure)

```
springboot-basics/
├── pom.xml                    # dependencies + build config (Maven)
├── mvnw, mvnw.cmd, .mvn/      # Maven wrapper: no Maven install needed
└── src/
    ├── main/
    │   ├── java/com/sunny/basics/     # your code (one package per lesson)
    │   └── resources/
    │       └── application.properties # settings
    └── test/java/com/sunny/basics/    # tests
```

## What's next

- Connect to a real database (MySQL or PostgreSQL) instead of H2
- Entity relationships: `@OneToMany`, `@ManyToOne`
- Pagination and sorting with `Pageable`
- Spring Security (login, JWT)
- API docs with Swagger / springdoc-openapi
