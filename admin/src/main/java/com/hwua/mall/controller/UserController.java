package com.hwua.mall.controller;

import com.alibaba.fastjson.JSONObject;

import com.hwua.commom.po.Auth;
import com.hwua.commom.po.User;
import com.hwua.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String lo(String userName, String password, HttpSession session){

        System.out.println("userName = " + userName);
        System.out.println("password = " + password);
        JSONObject jsonObject = new JSONObject();


        Map<String, Object> msg = userService.dologin(userName, password);

        if(msg.containsKey("error")){
            jsonObject.put("error",msg.get("error"));
        }else{
            jsonObject.put("success",msg.get("success"));
            session.setAttribute("user",msg.get("success"));
            User user = (User) session.getAttribute("user");
            Integer dbid = user.getDbid();
//            List<Auth> auths =  userService.getInfo(dbid);
//            System.out.println("auths = " + auths);
//            List<Auth> authOfType2 =userService.getAuthOfType2(dbid);
//            session.setAttribute("authOfType2",authOfType2);
//            System.out.println("auth = " + authOfType2);
//            session.setAttribute("auths",auths);

            System.out.println("dbid = " + dbid);
//            System.out.println("auths = " + auths);
        }


        return jsonObject.toJSONString();
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


}
