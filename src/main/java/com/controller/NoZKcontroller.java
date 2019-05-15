package com.controller;

import com.entity.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nn")
public class NoZKcontroller {
  @PostMapping("/noZK")
  public  JsonResult  error(){
    return  new JsonResult().NOZG("你没有权限",401);
  }
}
