package com.example.putian.service;

import com.example.putian.entity.User;
import com.example.putian.service.ex.PasswordNotMatchException;
import com.example.putian.service.ex.UpdateException;
import com.example.putian.service.ex.UserNotFoundException;

public interface IUserService {
    void reg(User user);

    User login(String username,String password)throws UserNotFoundException, PasswordNotMatchException;


    void changePassword(Integer uid,String oldPassword,String newPassword,String modifiedUser)throws
            UserNotFoundException,PasswordNotMatchException, UpdateException;


void changeInfo(Integer uid,String username,User user)throws UserNotFoundException,UpdateException;
User getByUid(Integer uid)throws UserNotFoundException;

}
