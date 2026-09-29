# Java & Spring Boot: Learning Journey

My notes and practice code while learning **Java** from scratch and then **Spring Boot**.
Every example has line-by-line comments and its real output.

## Roadmap

| Stage | Folder | What's inside |
|-------|--------|---------------|
| 1. Java basics | [`java-basics/`](java-basics/) | 33 topics: syntax, control flow, arrays, methods, OOP, collections, exceptions, lambdas, streams, records |
| 2. Spring Boot basics | [`springboot-basics/`](springboot-basics/) | 7 lessons: REST APIs, dependency injection, configuration, validation, exception handling, JPA + H2 database, testing |

## Setup (one time)

1. Install a JDK (Java Development Kit). On macOS with Homebrew:
   ```bash
   brew install openjdk@21
   ```
   Homebrew prints a `sudo ln -sfn ...` command at the end. Run it so `java` works everywhere.
2. Check it works:
   ```bash
   java -version
   ```
   If it says *"Unable to locate a Java Runtime"*, the JDK is installed but not linked yet:
   ```bash
   sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk
   ```

## How to run

**Java examples**: every example is a single `.java` file. From inside its folder:

```bash
# Option A (quickest, Java 11+): compile and run in one step
java HelloWorld.java

# Option B (the classic way): compile first, then run
javac HelloWorld.java   # creates HelloWorld.class (bytecode)
java HelloWorld         # runs the bytecode on the JVM
```

**Spring Boot app**:

```bash
cd springboot-basics
./mvnw spring-boot:run     # then open http://localhost:8080/hello
./mvnw test                # run the tests
```
