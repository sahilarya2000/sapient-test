package com.practice.template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthController {

  @GetMapping("/health")
  public ResponseEntity<String> healthCheck(){
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

}
