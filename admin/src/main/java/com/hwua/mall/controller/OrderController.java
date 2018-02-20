package com.hwua.mall.controller;

import com.hwua.mall.service.OrderService;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/status1")
    public String status1(Map map){

        List<Map<String, Object>> list = orderService.queryNoPay1();//代付款
        map.put("list",list);
        System.out.println("list =================== " + list);
        return "status1";
    }

    @RequestMapping("/status2")
    public String status2(Map map){
        List<Map<String, Object>> list = orderService.queryHavePay1();//代发货
        map.put("list",list);
        return "status2";
    }

    @RequestMapping("/status3")
    public String status3(Map map){
        List<Map<String, Object>> list = orderService.querySend1();//待收货
        map.put("list",list);
        return "status3";
    }

    @RequestMapping("/status4")
    public String status4(Map map){
        List<Map<String, Object>> list = orderService.queryNoConfirm1();//待评价
        map.put("list",list);
        return "status4";
    }

    @RequestMapping("/status5")
    public String status5(Map map){
        List<Map<String, Object>> list = orderService.querySuccess1();//交易成功
        map.put("list",list);
        return "status5";
    }
    @RequestMapping("/remind")
    public String remind(Map map, HttpSession session){
        List<Map<String, Object>> list = orderService.queryremind();//提醒发货
        session.setAttribute("msg",0);
        map.put("list",list);
        return "status2";
    }

    @RequestMapping("/go")
    public String go(Integer id){
        System.out.println("id = " + id);
       int i =  orderService.updateOrderSendTime(id);
       return "redirect:/order/status2";
    }

}
