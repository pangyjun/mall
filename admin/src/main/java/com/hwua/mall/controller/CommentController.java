package com.hwua.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.commom.dao.OrderMapper;
import com.hwua.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("feedback-list")
    public String feedback_list(Map map){
        List<Map> list =  orderService.queryAll();
        map.put("list",list);
      return "feedback-list";
    }

    @RequestMapping("/comment_del")
    public String comment_del(Integer id){
        System.out.println("**********commId = " + id);
        int i = orderService.comment_del(id);
        return "redirect:/comment/feedback-list";
    }

    @ResponseBody
    @RequestMapping(value = "/commentF" , produces = "application/json;charset=UTF-8")
    public String commentF(String id){

        System.out.println("id******* = " + id);
       int i =  orderService.setCommentF(id);
        JSONObject jsonObject = new JSONObject();
        return jsonObject.toJSONString();
    }

    @RequestMapping("/datadel")
    public String datadel(){
        int i = orderService.setDatadel();
        return "redirect:/comment/feedback-list";
    }
}
