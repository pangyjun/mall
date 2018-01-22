package com.hwua.front.service.impl;

import com.hwua.commom.dao.OrderMapper;
import com.hwua.commom.po.OrderDetail;
import com.hwua.commom.po.Orders;
import com.hwua.front.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int add(Orders orders) {
        return orderMapper.doInsert(orders);
    }

    @Override
    public int addDetail(OrderDetail orderDetail) {
        return orderMapper.doInsertDetail(orderDetail);
    }

    @Override
    public int doAddBatch(List<OrderDetail> list) {
        return orderMapper.doAddBatch(list);
    }

    @Override
    public int setPayTime(String oderid) {
        return orderMapper.setPayTime(oderid);
    }

    @Override
    public List queryNoPay(String id) {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryNoPay1(id);
        return returnList(list, maps);

    }

    @Override
    public List queryHavePay(String id) {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryHavePay1(id);
        return returnList(list, maps);


    }

    @Override
    public List querySend(String id) {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.querySend1(id);
        return returnList(list, maps);

    }

    @Override
    public List queryNoConfirm(String id) {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryNoConfirm1(id);
        return returnList(list, maps);

    }

    @Override
    public int del(String id, String orderid) {

        return orderMapper.delO2(orderid);

    }

    @Override
    public List<Map<String, Object>> querySuccess(String id) {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.querySuccess1(id);
        return returnList(list, maps);
    }

    @Override
    public int doRemind(String orderId) {
        return orderMapper.doRemind(orderId);
    }

    @Override
    public int doPay(String orderId) {
        return orderMapper.doPay(orderId);
    }

    @Override
    public int order_confirm(String orderId) {
        return orderMapper.order_confirm(orderId);
    }

    @Override
    public List<Map<String, Object>> queryByOrderId(String orderId) {
       return  orderMapper.queryNoPay2(orderId);
    }


    private List returnList(List list,List<Map<String,Object>> maps){
        if(maps != null){
            for (Map map : maps) {
                list.add(map);
                List<Map<String, Object>> maps2 = orderMapper.queryNoPay2(map.get("id"));
                map.put("list",maps2);
            }

        }
        return list;
    }
}
