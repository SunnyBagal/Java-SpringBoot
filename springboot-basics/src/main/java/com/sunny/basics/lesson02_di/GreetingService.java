package com.sunny.basics.lesson02_di;

/*
 * ============================================================
 *  LESSON 02: DEPENDENCY INJECTION (DI) & INVERSION OF CONTROL (IoC)
 *  File 1 of 4 - the interface
 * ============================================================
 *
 *  THE PROBLEM (without Spring):
 *      class GreetingController {
 *          private GreetingService service = new EnglishGreetingService();  // hard-wired!
 *      }
 *   The controller CREATES its own dependency. To switch to Hindi you must edit
 *   the controller, and in tests you can't swap in a fake service.
 *
 *  THE SPRING WAY:
 *   - Classes just SAY what they need (usually in their constructor).
 *   - Spring CREATES the objects and HANDS (injects) them in.
 *   - Control over object creation moves from YOUR code to SPRING
 *     -> "Inversion of Control". The objects Spring manages are called BEANS.
 *   - The place Spring keeps all beans = the "ApplicationContext" (IoC container).
 *
 *  We depend on this INTERFACE (Java topic 25), not on a concrete class.
 *  That's what makes swapping implementations easy.
 */
public interface GreetingService {

    String greet(String name);

    String language();
}
