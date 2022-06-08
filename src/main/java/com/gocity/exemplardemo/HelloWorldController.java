package com.gocity.exemplardemo;

import io.micrometer.core.annotation.Timed;
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

    @GetMapping
    @Timed(value="hello_world_timer", histogram = true)
    public ResponseEntity<String> helloWorld() throws Exception {
        int n = random.nextInt(399) + 1; // 1-400ms
        Thread.sleep(n);
        log.info("Hello world");
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
