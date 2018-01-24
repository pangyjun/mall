package com.hwua.commom.dao;


import com.hwua.commom.po.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<Product> queryOrder1(int i);

    List<Product> queryOrder2(int i);

    List<Product> queryOrder3(int i);

    List<Map> queryComm(String id);

    List<Product> query(HashMap<String, Object> map);

    int queryCount(HashMap<String, Object> map);
}
