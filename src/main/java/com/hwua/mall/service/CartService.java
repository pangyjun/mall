package com.hwua.mall.service;

import java.util.List;
import java.util.Map;

public interface CartService {
    public int add(String userId, String cId,String quantity);
    public List<Map<String,Object>> queryAll(String uId);

    int deleteCart(String userId, String cid);

    int deleteAll(String userId);

    int doadd(String id, String cid, String count);

    Map<String ,Object> querySelect(String uid, String cid);

    int setType(String uid, String cid,int i);

    List<Map<String,Object>> querySelect(String uid);

    int deleteSelect(String id);
}
