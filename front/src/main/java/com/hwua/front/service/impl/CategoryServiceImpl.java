package com.hwua.front.service.impl;


import com.hwua.commom.dao.CategoryMapper;
import com.hwua.front.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper ;

    public List<Map<String, Object>> QueryAll() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        System.out.println("categoryMapper = " + categoryMapper);
        List<Map<String, Object>> c1 = categoryMapper.queryC1();
        for(Map<String,Object> map1 : c1){
//            Map<String, Object> c1Map = new HashMap<>();
            System.out.println("map1 = " + map1);
            String c1Id = map1.get("ID").toString();
            List<Map<String, Object>> c2List = categoryMapper.queryC2(c1Id);
//            c1Map.put("c1",map1);
            ArrayList<Map<String, Object>> c2s = new ArrayList<>();
            map1.put("c2",c2s);

            for(Map<String,Object> map2 : c2List){
                c2s.add(map2);
                String c2Id = map2.get("ID").toString();
                List<Map<String, Object>> c3List = categoryMapper.queryC3(c1Id, c2Id);
                ArrayList<Map<String, Object>> c3s = new ArrayList<>();
                for (Map<String,Object> map3 : c3List) {
                    c3s.add(map3);
                }
                map2.put("c3",c3s);
            }
            list.add(map1);
        }

        return list;
    }
}
