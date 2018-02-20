package com.hwua.mall.service;

import com.hwua.commom.po.Product;

import java.util.List;

public interface ProductService {
    List<Product> queryAll();

    int doUpdateStop(Integer id);

    int doInsertProduct(Product product);

    Product queryById(Integer id);

    int product_del(Integer id);

    List<Product> querySearch(String search);
}
