package com.hwua.mall.service.impl;

import com.hwua.mall.dao.ProductMapper;
import com.hwua.mall.po.Product;
import com.hwua.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> queryByCategory(String c1, String c2, String c3) {
        return productMapper.queryByCategory(c1, c2, c3);
    }

    @Override
    public List<Product> queryAll() {

        return productMapper.queryAll();
    }

    @Override
    public List<Product> queryCupCake() {

        return productMapper.queryCupCake();
    }

    @Override
    public Product queryById(String id) {
        return productMapper.queryById(id);
    }

    @Override
    public List<Product> querySame(String id) {
        return productMapper.querySame(id);
    }

    @Override
    public List<Product> rearch(String search) {
        return productMapper.querySearch(search);
    }

    @Override
    public List<Product> queryAllLimit(Integer page) {
        return productMapper.queryLimit(page);
    }


    @Override
    public List<Product> queryNewCake2_8() {
        return productMapper.queryNew();
    }

    @Override
    public Product queryNewCake1() {
        return productMapper.query1();
    }

    @Override
    public Product queryNewCake2() {
        return productMapper.query2();
    }
}
