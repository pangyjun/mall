package com.hwua.mall.service;

import com.hwua.commom.po.Orders;
import com.hwua.commom.po.User;
import org.omg.CORBA.MARSHAL;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> dologin(String userName, String password);

    List<Map> queryAll();

    List<Orders> getmsg();


}
