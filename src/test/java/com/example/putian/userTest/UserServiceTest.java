package com.example.putian.userTest;

import com.example.putian.entity.User;
import com.example.putian.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserMapper mapper;
    @Test
    public void testUPdateUserInfo(){
        User user=new User();
        user.setUid(4);
       user.setEmail("6589@q.com");
       user.setGender(1);
       user.setModifiedUser("管理员");
        user.setPhone("123456");




        Integer i=mapper.updateInfo(user);
        System.err.println(i);

    }
    @Test
    public void tsetByUid(){
        Integer uid=6;
        User us = mapper.findByUid(uid);
        System.err.println(us);
    }



}
