package com.hwua.front.service;


import com.hwua.commom.po.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> queryByCategory(String c1, String c2, String c3);
    List<Product> queryAll();
    List<Product> queryCupCake();
    Product queryById(String id);

    List<Product> querySame(String id);

    List<Product> rearch(String search);
    public List<Product> queryAllLimit(Integer page);

    List<Product> queryNewCake2_8();

    Product queryNewCake1();

    Product queryNewCake2();

    List<Product> queryOrder1(Integer value);

    List<Product> queryOrder2(Integer value);

    List<Product> queryOrder3(Integer value);

    List<Map> queryComm(String id);

    List<Product> query(HashMap<String, Object> map);

    int queryCount(HashMap<String, Object> map);
}
