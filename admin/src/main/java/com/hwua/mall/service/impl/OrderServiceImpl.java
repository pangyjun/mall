package com.hwua.mall.service.impl;

import com.hwua.commom.dao.OrderMapper;
import com.hwua.commom.po.Comment;
import com.hwua.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<Map> queryAll() {
        orderMapper.setF();
        return orderMapper.queryAllComm();
    }

    @Override
    public int comment_del(Integer commId) {

        return orderMapper.comment_del(commId);
    }

    @Override
    public int setCommentF(String id) {

        Map map =  orderMapper.queryComm(id);
        String f = map.get("f").toString();
        if(Integer.parseInt(f) == 1){
            return orderMapper.setCommentF( id,0);
        }else{
            return orderMapper.setCommentF( id,1);
        }

    }

    @Override
    public int setDatadel() {
        return orderMapper.serDataDel();
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

    @Override
    public List queryNoPay1() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryNoPay11();
        return returnList(list, maps);

    }

    @Override
    public List queryHavePay1() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryHavePay11();
        return returnList(list, maps);


    }

    @Override
    public List querySend1() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.querySend11();
        return returnList(list, maps);

    }

    @Override
    public List queryNoConfirm1() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryNoConfirm11();
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
    public List<Map<String, Object>> querySuccess1() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.querySuccess11();
        return returnList(list, maps);
    }

    @Override
    public List<Map<String, Object>> queryremind() {
        ArrayList<Object> list = new ArrayList<>();
        List<Map<String, Object>> maps = orderMapper.queryqueryHavePay111();

//        int  i  = orderMapper.setRemind();
        return returnList(list, maps);
    }

    @Override
    public int updateOrderSendTime(Integer id) {
        return orderMapper.updateOrderSendTime(id);
    }

}
