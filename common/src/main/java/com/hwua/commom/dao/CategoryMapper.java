package com.hwua.commom.dao;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    /*
    * 查找Category1的所有分类
    * */
    public List<Map<String,Object>> queryC1();
    /*
    * 查找Category2的所有分类
    * */
    public List<Map<String,Object>> queryC2(String id1);
    /*
    * 查找Category3的所有分类
    * */
    public List<Map<String,Object>> queryC3(String id1, String id2);

}
