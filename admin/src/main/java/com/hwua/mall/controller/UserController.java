package com.hwua.mall.controller;

import com.alibaba.fastjson.JSONObject;

import com.hwua.commom.po.*;
import com.hwua.mall.service.AuthService;
import com.hwua.mall.service.OrderService;
import com.hwua.mall.service.RoleService;
import com.hwua.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Autowired
    private  OrderService orderService;


    @RequestMapping("/stop")
    public String stop(HttpSession session){
        session.invalidate();
        return "redirect:/user/welcome";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/admin-role")
    public String admin_role(Map map)
    {List<Map> list =  roleService.queryAllRole();
    map.put("list",list);
        return "admin-role";
    }
 @RequestMapping("/admin-permission")
    public String admin_permission(Map map){

        List<Auth> list = authService.queryAllAuth();
        map.put("list",list);
        return "admin-permission";
    }
 @RequestMapping("/admin-list")
    public String admin_list(Map map){

       List<Map> list =  userService.queryAll();
       map.put("list",list);
        return "admin-list";
    }
 @RequestMapping("/admin-add")
    public String admin_add(){
        return "admin-add";
    }














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

//            消息：订单发货提醒

            List<Map<String, Object>> list = orderService.queryremind();//提醒发货

        /* List<Auth> list =  userService.queryAuth(user.getDbid());*/

            session.setAttribute("msg",list.size());
            System.out.println("dbid = " + dbid);
//            System.out.println("auths = " + auths);
        }


        return jsonObject.toJSONString();
    }




}
