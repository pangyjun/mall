package com.hwua.mall.listener;

import com.hwua.mall.po.Product;
import com.hwua.mall.service.ProductService;
import com.hwua.mall.service.CategoryService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebListener
public class IndexListener implements ServletContextListener {




    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
/*
* (1)Listener的生命周期是由servlet容器（例如tomcat）管理的，
* 项目启动时上例中的ConfigListener是由servlet容器实例化并调用其contextInitialized方法，
* 而servlet容器并不认得@Autowired注解，因此导致ConfigService实例注入失败。
(2)而spring容器中的bean的生命周期是由spring容器管理的。

以下是获取service
*/
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        CategoryService categoryService = wac.getBean(CategoryService.class);
        ProductService productService = wac.getBean(ProductService.class);

        System.out.println("categoryService = " + categoryService);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("cbs",categoryService.QueryAll());



    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
