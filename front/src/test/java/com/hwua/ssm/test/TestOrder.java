package com.hwua.ssm.test;

import com.hwua.front.service.OrderService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestOrder
{

    @Test
    public void test1(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService bean = ctx.getBean(OrderService.class);
        List list = bean.queryNoConfirm("69");
        System.out.println(list);
    }
}
