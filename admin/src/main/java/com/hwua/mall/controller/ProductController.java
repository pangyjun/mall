package com.hwua.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/product")
public class ProductController {

    @RequestMapping("/product-category")
    public String product_category(){
        return "product-category";
    }

    @RequestMapping("/product-list")
    public String product_list(){
        return "product-list";
    }



}
