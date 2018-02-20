package com.hwua.mall.service.impl;

import com.hwua.commom.dao.ProductMapper;
import com.hwua.commom.po.Product;
import com.hwua.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> queryAll() {
        return productMapper.queryAll();
    }

    @Override
    public int doUpdateStop(Integer id) {
        Product product = productMapper.queryById(id.toString());
        if(product.getUseable() == 1){
            return productMapper.doUpdateStop(0,product.getId());
        }else{
            return productMapper.doUpdateStop(1,product.getId());
        }

    }

    @Override
    public int doInsertProduct(Product product) {
        return productMapper.doInsertProduct(product);
    }

    @Override
    public Product queryById(Integer id) {
        return productMapper.queryById(id.toString());
    }

    @Override
    public int product_del(Integer id) {
        return productMapper.product_del(id);
    }

    @Override
    public List<Product> querySearch(String search) {
        return productMapper.querySearch(search);
    }
}
