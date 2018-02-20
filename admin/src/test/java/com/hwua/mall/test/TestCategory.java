package com.hwua.mall.test;

import com.alibaba.fastjson.JSON;
import com.hwua.mall.service.CategoryService;
import com.hwua.mall.service.RoleService;
import com.hwua.mall.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TestCategory {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryService bean = ctx.getBean(CategoryService.class);
        List<Map> query = bean.query();
        System.out.println(JSON.toJSONString(query));


    }
    @Test
    public void test2(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RoleService bean = ctx.getBean(RoleService.class);
        List<Map> query = bean.queryAllRole();
        System.out.println(JSON.toJSONString(query));


    }
    @Test
    public void test3(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = ctx.getBean(UserService.class);
        List<Map> query = bean.queryAll();
        System.out.println(JSON.toJSONString(query));


    }
}
