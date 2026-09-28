# Java Basics

33 small, runnable lessons. Each `.java` file has:

- a **header** explaining the topic (what, why, key rules)
- **line-by-line comments** saying what each line does
- the **exact OUTPUT** at the bottom of the file (checked by actually running it)

Go in order. Each topic builds on the previous ones.

## How to run a lesson

```bash
cd java-basics/01_HelloWorld
java HelloWorld.java
```

Tip: before running, try to predict the output, then compare with the OUTPUT block.
Then change something and run it again. That's how it sticks.

## Topics

### Part 1: Fundamentals
| # | Topic | File |
|---|-------|------|
| 01 | Hello World, `main`, `println` | [HelloWorld.java](01_HelloWorld/HelloWorld.java) |
| 02 | Variables, `final`, `var` | [Variables.java](02_Variables/Variables.java) |
| 03 | Primitive & reference data types | [DataTypes.java](03_DataTypes/DataTypes.java) |
| 04 | Type casting, parsing strings | [TypeCasting.java](04_TypeCasting/TypeCasting.java) |
| 05 | Operators | [Operators.java](05_Operators/Operators.java) |
| 06 | User input with `Scanner` | [UserInput.java](06_UserInput/UserInput.java) |
| 07 | Strings | [Strings.java](07_Strings/Strings.java) |

### Part 2: Control flow
| # | Topic | File |
|---|-------|------|
| 08 | if / else if / else | [IfElse.java](08_IfElse/IfElse.java) |
| 09 | switch (classic & modern) | [SwitchStatement.java](09_Switch/SwitchStatement.java) |
| 10 | for, while, do-while, for-each | [Loops.java](10_Loops/Loops.java) |
| 11 | break, continue, labels | [BreakContinue.java](11_BreakContinue/BreakContinue.java) |

### Part 3: Arrays & methods
| # | Topic | File |
|---|-------|------|
| 12 | Arrays | [Arrays1D.java](12_Arrays/Arrays1D.java) |
| 13 | 2D arrays | [Arrays2D.java](13_MultiDimensionalArrays/Arrays2D.java) |
| 14 | Methods, return, pass-by-value | [Methods.java](14_Methods/Methods.java) |
| 15 | Method overloading | [MethodOverloading.java](15_MethodOverloading/MethodOverloading.java) |
| 16 | Recursion | [Recursion.java](16_Recursion/Recursion.java) |

### Part 4: Object-Oriented Programming (most important for Spring Boot)
| # | Topic | File |
|---|-------|------|
| 17 | Classes & objects | [ClassesAndObjects.java](17_ClassesAndObjects/ClassesAndObjects.java) |
| 18 | Constructors | [Constructors.java](18_Constructors/Constructors.java) |
| 19 | `this` keyword | [ThisKeyword.java](19_ThisKeyword/ThisKeyword.java) |
| 20 | `static` keyword | [StaticKeyword.java](20_StaticKeyword/StaticKeyword.java) |
| 21 | Access modifiers & encapsulation | [Encapsulation.java](21_Encapsulation/Encapsulation.java) |
| 22 | Inheritance | [Inheritance.java](22_Inheritance/Inheritance.java) |
| 23 | Polymorphism | [Polymorphism.java](23_Polymorphism/Polymorphism.java) |
| 24 | Abstraction (abstract classes) | [Abstraction.java](24_Abstraction/Abstraction.java) |
| 25 | Interfaces | [Interfaces.java](25_Interfaces/Interfaces.java) |

### Part 5: Everyday Java
| # | Topic | File |
|---|-------|------|
| 26 | Exception handling | [ExceptionHandling.java](26_ExceptionHandling/ExceptionHandling.java) |
| 27 | ArrayList | [ArrayListBasics.java](27_ArrayList/ArrayListBasics.java) |
| 28 | HashMap & HashSet | [MapAndSet.java](28_HashMapAndHashSet/MapAndSet.java) |
| 29 | Enums | [Enums.java](29_Enums/Enums.java) |
| 30 | Wrapper classes & autoboxing | [WrapperClasses.java](30_WrapperClasses/WrapperClasses.java) |
| 31 | StringBuilder | [StringBuilderBasics.java](31_StringBuilder/StringBuilderBasics.java) |
| 32 | Lambdas & streams | [LambdasAndStreams.java](32_LambdasAndStreams/LambdasAndStreams.java) |
| 33 | Records | [Records.java](33_Records/Records.java) |

After this, move on to [`springboot-basics/`](../springboot-basics/).
