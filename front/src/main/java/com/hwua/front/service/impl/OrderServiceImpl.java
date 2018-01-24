package com.hwua.front.service.impl;

import com.hwua.commom.dao.OrderMapper;
import com.hwua.commom.po.Address;
import com.hwua.commom.po.Comment;
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
        if(maps != null){
            for (Map map : maps) {
                list.add(map);
                List<Map<String, Object>> maps2 = orderMapper.query(map.get("id"));
                map.put("list",maps2);
            }

        }
        return list;

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
       return  orderMapper.query(orderId);
    }

    @Override
    public int diInsertComm(Comment comment) {
        return orderMapper.doInsertComm(comment);
    }

    @Override
    public int doUpdateOderDetail(Integer oid, int cid) {

        return orderMapper.doUpdateOderDetail(oid,cid);
    }

    @Override
    public int setSucc(Integer orderId) {
        return orderMapper.setSucc(orderId);
    }

    @Override
    public List<Address> selectAddr(Integer id) {
        return orderMapper.selectAddr(id);
    }

    @Override
    public List<Map> selectComm(Integer mid) {
        return orderMapper.selectComm(mid);
    }

    @Override
    public Address queryAddress(Integer id) {

        return orderMapper.queryAddress(id);
    }

    @Override
    public List<Address> queryAddressList(Integer id) {
        return orderMapper.queryAddressList(id);
    }

    @Override
    public int updateAddr(Integer id, Address address) {
        return orderMapper.updateAddr( id,  address) ;
    }

    @Override
    public Address queryAddress1(Integer mid,Address address2) {
        return orderMapper.queryAddress1(mid,address2);
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
