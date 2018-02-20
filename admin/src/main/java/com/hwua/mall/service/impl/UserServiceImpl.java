package com.hwua.mall.service.impl;

import com.hwua.commom.dao.UserMapper;
import com.hwua.commom.po.Orders;
import com.hwua.commom.po.User;
import com.hwua.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Map> queryAll() {
        List<Map> list = userMapper.queryAll();
        for (Map m1:list) {
            List<Map> list1 = userMapper.queryuserofRole(m1.get("dbid"));
            m1.put("role","");
            for (Map m2 : list1) {
                m1.put("role",m1.get("role")+""+m2.get("roleName")+",");

            }
            String user = m1.get("role").toString();
            if(user.contains(",")){
                int i = user.lastIndexOf(",");
                System.out.println("i = " + i);
                String substring = user.substring(0,i);
                m1.put("role",substring);
            }
        }
        return list;
    }

    @Override
    public List<Orders> getmsg() {
        return userMapper.getmsg();
    }


}
