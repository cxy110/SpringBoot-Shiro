package com.entity;

import lombok.Data;

@Data
public class JsonResult {
  String message="OK";
  int code=200;
  Object data;
  public JsonResult (String message){
    this.message=message;
    code= 500;
    data=null;
  }
  public JsonResult exceptions(String message){
  return  new JsonResult(message);
  }
  public  JsonResult(Throwable e,int code){
    this.message=e.getMessage();
    this.code=code;
  }
  public JsonResult(Object data){

    this.data=data;

  }
  public JsonResult(){
  }
  public JsonResult(String message,int code){
    this.message=message;
    this.code=code;
  }
public JsonResult NOZG(String message,int code){
    return new JsonResult(message,code);
}
  public JsonResult OK(Object data){
return  new JsonResult(data);
  }

}
