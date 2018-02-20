package com.hwua.mall.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Map> queryAll();


    int comment_del(Integer commId);

    int setCommentF(String id);

    int setDatadel();

    List<Map<String,Object>> queryNoPay1();

    List<Map<String,Object>> queryHavePay1();

    List<Map<String,Object>> querySend1();

    List<Map<String,Object>> queryNoConfirm1();

    List<Map<String,Object>> querySuccess1();

    List<Map<String,Object>> queryremind();

    int updateOrderSendTime(Integer id);
}
