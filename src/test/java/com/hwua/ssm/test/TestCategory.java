package com.hwua.ssm.test;

import com.hwua.mall.service.CategoryService;
import com.hwua.mall.service.impl.CategoryServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TestCategory {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryServiceImpl bean = ctx.getBean(CategoryServiceImpl.class);
        System.out.println("bean = " + bean);
        List<Map<String, Object>> maps = bean.QueryAll();
        for (Map map:maps) {
            System.out.println("map = " + map);
        }
    }


}
