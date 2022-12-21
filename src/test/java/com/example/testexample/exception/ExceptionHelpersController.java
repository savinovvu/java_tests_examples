package com.example.testexample.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHelpersController {

    @GetMapping("/web/exception/execute")
    public ResponseEntity<String> doSomething() {
        return ResponseEntity.ok("ok");
    }
}
