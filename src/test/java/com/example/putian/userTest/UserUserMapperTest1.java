package com.example.putian.userTest;

import com.example.putian.entity.User;
import com.example.putian.mapper.UserMapper;
import com.example.putian.service.IUserService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserUserMapperTest1 {
    @Autowired
    UserMapper userMapper;

    @Autowired
    IUserService userService;

    @Autowired
    DataSource ds;
    @Test
    public void tstds() throws SQLException {
        Connection connection = ds.getConnection();
        System.err.println(connection);
    }
    @Test
    public void testMapper(){
        User user = new User();
        user.setUsername("JJKK");
        user.setPassword("wowo");
        user.setEmail("8985223");
        int i= userMapper.addNew(user);
        System.err.println(i);
    }
    @Test
    public void tstFind(){
        User user= userMapper.findByUsername("JJKK");
        System.out.println(user);
    }

    @Test
    public void TESTUPpwd(){
           userService.changePassword(5, "8888", "9999", "lucy");



    }

    @Test
    public void login(){
        try {
            String username="90tt";
            String password="211";
            User user=userService.login(username, password);
            System.err.println(user);

        }catch (Exception e){
            System.err.println(e.getClass()
            .getName());
            System.err.println(e.getMessage());
        }


    }

}
