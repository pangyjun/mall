package com.hwua.commom.dao;



import com.hwua.commom.po.Address;
import com.hwua.commom.po.Comment;
import com.hwua.commom.po.OrderDetail;
import com.hwua.commom.po.Orders;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    int doInsert(Orders orders);

    int doInsertDetail(OrderDetail orderDetail);

    int doAddBatch(List<OrderDetail> list);

    int setPayTime(String oderid);

    List<Map<String,Object>> queryNoPay(String id);

    List<Map<String,Object>> queryNoPay1(String id);

    List<Map<String,Object>> queryNoPay2(Object id);
    List<Map<String,Object>> query(Object id);


    List<Map<String,Object>> querySuccess1(String id);

    List<Map<String,Object>> querySend1(String id);

    List<Map<String,Object>> queryHavePay1(String id);

    List<Map<String,Object>> queryNoConfirm1(String id);

    int delO2(String orderid);

    int doRemind(String orderId);

    int doPay(String orderId);

    int order_confirm(String orderId);

    List<Map<String,Object>> queryByOrderId(String orderId);

    int doInsertComm(Comment comment);

    int doUpdateOderDetail(Integer oid, int cid);

    int setSucc(Integer orderId);

    List<Address> selectAddr(Integer id);

    List<Map> selectComm(Integer mid);

    Address queryAddress(Integer id);

    List<Address> queryAddressList(Integer id);

    int updateAddr(Integer id, Address address);

    Address queryAddress1(Integer mid,Address address2);
}
