package com.example.hashirun;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController  {
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello");
    }
     @GetMapping("/users")
    public List<String> users() {
        return List.of("Taro", "Jio", "Saburo");

    }
}
