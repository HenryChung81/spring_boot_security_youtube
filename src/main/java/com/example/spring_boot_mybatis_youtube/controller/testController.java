package com.example.spring_boot_mybatis_youtube.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

  @GetMapping("/test")
  public String hello() {
    return "hello";
  }
  
}
