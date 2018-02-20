package com.hwua.mall.service.impl;

import com.hwua.commom.dao.CategoryMapper;
import com.hwua.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;



    @Override
    public List<Map> query() {
        List<Map> list = new ArrayList<>();
        List<Map<String, Object>> c1 = categoryMapper.queryC1();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("pId",0);
        map.put("name","分类");
        map.put("open",true);
        list.add(map);
        int i = 11;
        Integer temp = 0;
        String temp1 = "";
        for(Map<String,Object> map1 : c1){
            map1.put("name",map1.get("NAME"));
            map1.put("id1",map1.get("ID"));

            temp = i;
            map1.put("pId",1);
            map1.put("id", i++);

            list.add(map1);
            String c1Id = map1.get("ID").toString();
            List<Map<String, Object>> c2List = categoryMapper.queryC2(c1Id);
            int p = 1;

            for(Map<String,Object> map2 : c2List){

                map2.put("name",map2.get("NAME"));
                map2.put("id2",map2.get("ID"));
                map2.put("id1",map1.get("ID"));

                map2.put("pId", temp);


                temp1 = temp.toString()+ p++;
                map2.put("id",Integer.parseInt(temp1));

               list.add(map2);
                String c2Id = map2.get("ID").toString();
                List<Map<String, Object>> c3List = categoryMapper.queryC3(c1Id, c2Id);
                ArrayList<Map<String, Object>> c3s = new ArrayList<>();
                int o = 1;
                for (Map<String,Object> map3 : c3List) {
                    map3.put("name",map3.get("NAME"));
                    map3.put("id3",map3.get("ID"));
                    map3.put("id2",map2.get("ID"));
                    map3.put("id1",map1.get("ID"));
                    map3.put("pId",Integer.parseInt(temp1) );
                    System.out.println("--------------------1-------------------------");
                    map3.put("id",Integer.parseInt(temp1.toString()+ o++));

                    list.add(map3);
                }
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> queryc1() {
        return categoryMapper.queryC1();
    }

    @Override
    public List<Map<String, Object>> queryc2(Object id) {
        return categoryMapper.queryC2(id.toString());

    }

    @Override
    public int add(String name) {

        Map m= categoryMapper.query(name);
        if(m == null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",name);
            int j = categoryMapper.addc1(map);
            Object id = map.get("id");
            System.out.println(map+"``````````````");
            int i =  categoryMapper.add_relation(map);
            return i+j;
        }else {
            return 0;
        }

    }

    @Override
    public int addc2(Integer c1, String name) {
        Map m= categoryMapper.queryc2(name);
        if(m == null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",name);
            map.put("c1",c1);
            int j = categoryMapper.addc2(map);
            Object id = map.get("id");
            System.out.println(map+"``````````````");
            int i =  categoryMapper.add_relation1(map);
            return i+j;
        }else {
            return 0;
        }


    }


}
