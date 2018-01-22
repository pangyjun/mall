package com.hwua.front.service;



import com.hwua.commom.po.OrderDetail;
import com.hwua.commom.po.Orders;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int add(Orders orders);

    int addDetail(OrderDetail orderDetail);

    int doAddBatch(List<OrderDetail> list);

    int setPayTime(String oderid);

    List queryNoPay(String id);

    List queryHavePay(String id);

    List querySend(String id);

    List queryNoConfirm(String id);


    int del(String id, String orderid);

    List<Map<String,Object>> querySuccess(String id);

    int doRemind(String orderId);

    int doPay(String orderId);

    int order_confirm(String orderId);

    List<Map<String,Object>> queryByOrderId(String orderId);
}
