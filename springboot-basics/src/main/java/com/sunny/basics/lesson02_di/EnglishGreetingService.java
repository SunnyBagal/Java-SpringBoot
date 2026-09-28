package com.sunny.basics.lesson02_di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/*
 * ============================================================
 *  LESSON 02 - File 2 of 4: a bean implementation
 * ============================================================
 *
 *  @Service -> "Spring, please create an object of this class and manage it."
 *              Spring finds it during component scanning at startup.
 *
 *  STEREOTYPE ANNOTATIONS (all make a class a bean; the name documents its role):
 *     @Component       -> generic bean
 *     @Service         -> business logic
 *     @Repository      -> database access (lesson 06)
 *     @RestController  -> handles web requests (lesson 01)
 *
 *  @Primary -> there are TWO GreetingService beans (English and Hindi).
 *              When someone asks for "a GreetingService", Spring can't guess which
 *              one... so @Primary marks this one as the DEFAULT choice.
 *
 *  By default every bean is a SINGLETON: Spring creates exactly ONE object
 *  and shares it everywhere it is injected.
 */
@Service
@Primary
public class EnglishGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

    @Override
    public String language() {
        return "English";
    }
}
