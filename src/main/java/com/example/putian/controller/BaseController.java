package com.example.putian.controller;

import com.example.putian.service.ex.*;
import com.example.putian.util.JsonResult;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseController {
    static final String SESSION_UID="uid";
    static final String SESSION_USERNAME="username";
    static final Integer SUCCESS=20;
    static  final Integer ERROR_USERNAME_DUPLICATE=30;
//    static final  Integer ERROR_INSERT_USER=40;


   @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult handleException(Throwable e){


       JsonResult<Void> jr = new JsonResult<>(e.getMessage());

      if (e instanceof UsernameDuplicateException){
          jr.setState(ERROR_USERNAME_DUPLICATE);


      }else  if (e instanceof UserNotFoundException){
          jr.setState(31);
          jr.setMessage(e.getMessage());
      }else if (e instanceof PasswordNotMatchException
      ){
          jr.setState(32);
          jr.setMessage(e.getMessage());
      }else if(e instanceof InsertException){

          jr.setState(40);
          jr.setMessage(e.getMessage());

      }else if (e instanceof UpdateException){
          jr.setState(41);
      }






       return jr;


   }





}
