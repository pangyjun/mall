package com.hwua.mall.dao;

import java.util.List;
import java.util.Map;

public interface CartMapper {


    int doUpdate(String id, String cId, String quantity);


    public List<Map<String,Object>> queryAll(String userId);

    Map queryByCid(String cId,String uId);

    int update(int count,String mid);

    int doDelete(String userId, String cid);

    int doDeleteAll(String userId);


    int doCount(String id, String cid, int count);

    int setType(String uid, String cid,int i);

    List<Map<String,Object>> querySelect(String uid);

    int deleteSelete(String id);
}
