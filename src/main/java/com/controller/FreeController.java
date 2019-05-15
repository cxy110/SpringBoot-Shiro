package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/free")
public class FreeController {
  @PostMapping("/freeone")
  public String freeone(){
    return "one";
  }
  @PostMapping("/freetwo")
  public String freetwo(){
    return "freetwo";
  }
}
