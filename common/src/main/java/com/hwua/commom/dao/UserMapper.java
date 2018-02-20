package com.hwua.commom.dao;

import com.hwua.commom.po.Orders;
import com.hwua.commom.po.User;
import org.omg.CORBA.MARSHAL;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public Map<String, String> queryByName(String userName) ;

    User queryUser(String userName, String pass);

    List<Map> queryAll();
    List<Map> queryuserofRole(Object uid);

    List<Orders> getmsg();
}
