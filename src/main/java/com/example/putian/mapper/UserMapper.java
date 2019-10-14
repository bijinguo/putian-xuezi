package com.example.putian.mapper;

import com.example.putian.entity.User;
import com.example.putian.service.ex.UpdateException;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.Date;

public interface UserMapper {

    Integer addNew(User user);
    User findByUsername(String username);
    User findByUid(Integer uid);
    Integer updatePassword(@Param("uid") Integer uid, @Param("password")String password,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);


    Integer updateInfo(User user) ;
}
