package ru.alex.http3demo.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Http3Controller {
    @GetMapping("/hello")
    String hello() {
        return "Hello HTTP/3!";
    }
}
