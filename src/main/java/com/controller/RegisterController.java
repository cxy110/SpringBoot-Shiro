package com.controller;

import com.entity.JsonResult;
import com.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
  @Autowired
  private RegisterService registerService;
  @PostMapping("/reg/{username}/{password}")

  public JsonResult register(@PathVariable String username, @PathVariable String password){
     Integer row =registerService.registeruser(username,password);

       JsonResult jsonResult = new JsonResult() ;

       return jsonResult;


  }

}
