package com.gocity.exemplardemo;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloWorldController {

    private final Random random = new Random();
    private static Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private final MeterRegistry registry;

    public HelloWorldController(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping
    @Timed(value="scan_timer", histogram = true)
    public ResponseEntity<String> helloWorld() throws Exception {
        int n = random.nextInt(349) + 1; // 1-400ms
        Thread.sleep(n);
        log.debug("Hello world");

//        Counter.builder("hello_world_counter")
//                .register(registry)
//                .increment();

        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
