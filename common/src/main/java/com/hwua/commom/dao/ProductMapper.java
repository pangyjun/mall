package com.hwua.commom.dao;


import com.hwua.commom.po.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> queryByCategory(String c1, String c2, String c3);
    List<Product> queryAll();
    List<Product> queryCupCake();
    Product queryById(String id);

    List<Product> querySame(String id);

    List<Product> querySearch(String search);

    List<Product> queryLimit(Integer start);

    List<Product> queryNew();

    Product query1();

    Product query2();
}
