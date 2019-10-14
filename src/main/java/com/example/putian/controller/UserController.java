package com.example.putian.controller;

import com.example.putian.entity.User;
import com.example.putian.service.IUserService;
import com.example.putian.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{


    @Autowired
    private IUserService userService;
    @PostMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(SUCCESS);



    }
    @PostMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user=userService.login(username, password);

       session.setAttribute(SESSION_UID,user.getUid());
       session.setAttribute(SESSION_USERNAME,user.getUsername());

        JsonResult<User> jsonResult = new JsonResult();
        jsonResult.setData(user);
        jsonResult.setState(SUCCESS);

        return  jsonResult;
    }


    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(
            HttpSession session, @RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){

        Integer uid =Integer.valueOf( session.getAttribute(SESSION_UID).toString());
        String username = session.getAttribute(SESSION_USERNAME).toString();


        userService.changePassword(uid, oldPassword, newPassword, username);


        return new JsonResult<Void>(SUCCESS);







    }
    @PostMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid=Integer.valueOf(session.getAttribute(SESSION_UID).toString());
        String username = session.getAttribute(SESSION_USERNAME).toString();
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(SUCCESS);


    }

@GetMapping("get_by_uid")
    public JsonResult<User> getByUiD(HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute(SESSION_UID).toString());
        User user = userService.getByUid(uid);
        return new JsonResult<User>(SUCCESS,user);


    }









}
