package com.sunny.basics.lesson02_di;

import org.springframework.stereotype.Service;

/*
 * ============================================================
 *  LESSON 02 - File 3 of 4: a second implementation
 * ============================================================
 *
 *  @Service("hindi") -> a bean with an explicit NAME "hindi".
 *  Anyone who specifically wants THIS one asks for it with @Qualifier("hindi").
 *  (Without a name, the bean name would be "hindiGreetingService".)
 */
@Service("hindi")
public class HindiGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Namaste, " + name + "!";
    }

    @Override
    public String language() {
        return "Hindi";
    }
}
