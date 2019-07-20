package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name) {
        if (null == name) {
            name = "boy";
        }
        return "hello world" + name;
    }

}
