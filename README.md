# Java & Spring Boot — Learning Journey

My notes and practice code while learning **Java** from scratch and then **Spring Boot**.

## Roadmap

| Stage | Folder | Status |
|-------|--------|--------|
| 1. Java basics | [`basics/`](basics/) | In progress |
| 2. Advanced Java (collections, streams, generics) | coming soon | Planned |
| 3. Spring Boot | coming soon | Planned |

## Setup (one time)

1. Install a JDK (Java Development Kit). On macOS with Homebrew:
   ```bash
   brew install openjdk@21
   ```
2. Check it works:
   ```bash
   java -version
   ```

## How to run any example

Every example is a single `.java` file. From inside its folder:

```bash
# Option A (quickest, Java 11+): compile and run in one step
java HelloWorld.java

# Option B (the classic way): compile first, then run
javac HelloWorld.java   # creates HelloWorld.class (bytecode)
java HelloWorld         # runs the bytecode on the JVM
```
