package com.hwua.mall.service;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Map> query();

    List<Map<String, Object>> queryc1();

    List<Map<String,Object>> queryc2(Object id);

    int add(String name);

    int addc2(Integer c1, String name);
}
