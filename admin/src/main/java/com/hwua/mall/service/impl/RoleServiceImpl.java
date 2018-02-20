package com.hwua.mall.service.impl;

import com.hwua.commom.dao.RoleMapper;
import com.hwua.commom.po.Role;
import com.hwua.mall.service.AuthService;
import com.hwua.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Map> queryAllRole() {
        List<Map> list = roleMapper.queryAllRole();
        for (Map m1 : list) {
            List<Map> list1 = roleMapper.queryRoleofUser(m1.get("dbid"));
            m1.put("user","");
            for (Map m2 : list1) {
                m1.put("user",m1.get("user")+""+m2.get("username")+",");

            }
            String user = m1.get("user").toString();
            if(user.contains(",")){
                int i = user.lastIndexOf(",");
                System.out.println("i = " + i);
                String substring = user.substring(0,i);
                m1.put("user",substring);
            }


        }
        return list;
    }
}
