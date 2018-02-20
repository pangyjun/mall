package com.hwua.commom.dao;

import java.util.HashMap;
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





    int addc1(Map<String, Object> map);

    int add_relation(HashMap<String, Object> map);

    Map query(String name);

    Map queryc2(String name);

    int addc2(HashMap<String, Object> map);

    int add_relation1(HashMap<String, Object> map);
}
