package com.example.putian.service.impl;

import com.example.putian.entity.User;
import com.example.putian.mapper.UserMapper;
import com.example.putian.service.IUserService;
import com.example.putian.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void reg(User user) throws UsernameDuplicateException,InsertException {

        String  username=user.getUsername();
        User byUsername = userMapper.findByUsername(username);
        if (byUsername!=null){
            throw new UsernameDuplicateException("用户已存在,请重新注册");

        }


        String uuid=UUID.randomUUID().toString();
        user.setSalt(uuid);
        String password=getUUIDPassword(uuid,user.getPassword());
        user.setPassword(password);
        user.setCreatedUser(username);
        user.setIsDelete(0);
        Date now=new Date();
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setCreatedTime(now);
        Integer i = userMapper.addNew(user);
        if (!i.equals(1)){
            throw new InsertException("用户注册异常！");
        }


    }

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {

            User user=userMapper.findByUsername(username);
            if (user==null){
                throw new UserNotFoundException("用户不存在");

            }
            if (user.getIsDelete().equals(1)){
                throw new UserNotFoundException("用户已删除");
            }
            String salt=user.getSalt();
            String  jmpwd=getUUIDPassword(salt, password);
            if (!jmpwd.equals(user.getPassword())){
                throw new PasswordNotMatchException("密码错误");
            }

            user.setSalt(null);
            user.setPassword(null);
            user.setIsDelete(null);

        return user;
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword, String modifiedUser) throws
            UserNotFoundException, PasswordNotMatchException, UpdateException {

        User user=userMapper.findByUid(uid);
        if (uid==null){
            throw new UserNotFoundException("用户不存在了");
        }
        if (user.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户已不存在");
        }
        String salt=user.getSalt();

        String pwd=getUUIDPassword(salt, oldPassword);
        if (!pwd.equals(user.getPassword())){
            throw new  PasswordNotMatchException("修改错误，原密码不正确");
        }
        String pd=getUUIDPassword(salt, newPassword);

        Integer i=userMapper.updatePassword(uid, pd, modifiedUser,new Date());
        if (!i.equals(1)){
            throw new UpdateException("密码更新失败");
        }







    }

    @Override
    public void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException {
        User user1=userMapper.findByUid(uid);
        if (user1==null){
            throw new  UserNotFoundException("更改的用户不存在");

        }
        if(user1.getIsDelete().equals(1)){
            throw  new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);

        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer i=userMapper.updateInfo(user);
        if (i!=1){
            throw new UpdateException("用户更新异常");
        }



    }

    @Override
    public User getByUid(Integer uid) throws UserNotFoundException {
        User user=userMapper.findByUid(uid);
        if (user==null){
            throw new  UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户已删除");

        }
        User user1 = new User();
        user1.setIsDelete(user.getIsDelete());
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());



        return user1;
    }

    private String getUUIDPassword(String uuid, String password) {
        String up=uuid+password+uuid;
        for (int i=0;i<3;i++){
           up= DigestUtils.md5DigestAsHex(up.getBytes());

        }


        return  up;
    }
}
