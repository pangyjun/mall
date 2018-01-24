package com.hwua.mall.service.impl;

import com.hwua.commom.dao.UserMapper;
import com.hwua.commom.po.User;
import com.hwua.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> dologin(String userName, String password) {
        HashMap<String, Object> map = new HashMap<>();
        Map<String,String> username= userMapper.queryByName(userName);
        System.out.println("username = " + username);
        if(username != null){
            String pass = DigestUtils.md5DigestAsHex(password.getBytes());
            User user =  userMapper.queryUser(userName,pass);
            System.out.println("user = " + user);
            if(user!= null){
                map.put("success",user);
            }else{
                map.put("error","密码错误");
            }

        }else{
            map.put("error","没有此用户");
        }


        return map;
    }
}
