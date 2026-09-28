package com.sunny.basics.lesson02_di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
 * ============================================================
 *  LESSON 02 - File 4 of 4: injecting beans into a controller
 * ============================================================
 *
 *  CONSTRUCTOR INJECTION (the recommended way):
 *   - Declare dependencies as private final fields.
 *   - Ask for them in the constructor.
 *   - Spring sees the constructor, finds matching beans and passes them in.
 *   - No "new" anywhere! If a class has only ONE constructor, no annotation is needed.
 *
 *  Other ways you'll see in old code (avoid them):
 *     @Autowired on a field:   @Autowired private GreetingService service;
 *   Harder to test and the field can't be final.
 *
 *  @RequestMapping("/di") on the class -> every URL in this class starts with /di
 *  @PathVariable -> read a value from the URL PATH: /di/greet/Sunny -> name = "Sunny"
 */
@RestController
@RequestMapping("/di")
public class GreetingController {

    private final GreetingService defaultService;   // will be English (@Primary)
    private final GreetingService hindiService;     // will be Hindi (@Qualifier)
    private final List<GreetingService> allServices;    // Spring can inject ALL beans of a type!
    private final ApplicationContext context;

    // Spring calls this constructor and supplies all 4 arguments itself
    public GreetingController(GreetingService defaultService,
                              @Qualifier("hindi") GreetingService hindiService,
                              List<GreetingService> allServices,
                              ApplicationContext context) {
        this.defaultService = defaultService;
        this.hindiService = hindiService;
        this.allServices = allServices;
        this.context = context;
    }

    // GET /di/greet/Sunny
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return defaultService.greet(name);
    }

    // GET /di/greet/Sunny/hindi
    @GetMapping("/greet/{name}/hindi")
    public String greetHindi(@PathVariable String name) {
        return hindiService.greet(name);
    }

    // GET /di/languages -> uses the injected list of every GreetingService bean
    @GetMapping("/languages")
    public List<String> languages() {
        return allServices.stream()
                .map(GreetingService::language)
                .sorted()
                .toList();
    }

    // GET /di/singleton -> proves Spring gives the SAME object each time
    @GetMapping("/singleton")
    public Map<String, Object> singleton() {
        GreetingService a = context.getBean(EnglishGreetingService.class);
        GreetingService b = context.getBean(EnglishGreetingService.class);
        return Map.of(
                "sameObject", a == b,                   // true: one shared instance
                "injectedIsSame", a == defaultService    // the one we got injected, too
        );
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * $ curl http://localhost:8080/di/greet/Sunny
 * Hello, Sunny!
 *
 * $ curl http://localhost:8080/di/greet/Sunny/hindi
 * Namaste, Sunny!
 *
 * $ curl http://localhost:8080/di/languages
 * ["English","Hindi"]
 *
 * $ curl http://localhost:8080/di/singleton
 * {"sameObject":true,"injectedIsSame":true}
 *   (key order may differ - Map.of doesn't keep order)
 * ----------------------------------------------------------
 */
