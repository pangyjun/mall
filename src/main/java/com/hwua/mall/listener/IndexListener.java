package com.hwua.mall.listener;

import com.hwua.mall.po.Product;
import com.hwua.mall.service.ProductService;
import com.hwua.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.Map;

@WebListener
public class IndexListener implements ServletContextListener {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("cbs",categoryService.QueryAll());
        List<Product> maps = productService.queryNewCake2_8();
        Product map1 = productService.queryNewCake1();
        Product map2 = productService.queryNewCake2();
        servletContext.setAttribute("new8_10",maps);
        servletContext.setAttribute("one",map1);
        servletContext.setAttribute("two",map2);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
