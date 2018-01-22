package com.hwua.ssm.test;

import com.hwua.front.service.CartService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TestCart {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService bean = ctx.getBean(CartService.class);
        System.out.println("bean = " + bean);
        int add = bean.add("71", "140", "2");
        System.out.println("add = " + add);
    }


}
