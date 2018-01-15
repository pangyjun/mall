package com.hwua.mall.service.impl;

import com.hwua.mall.dao.CartMapper;
import com.hwua.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cartService")
public class CartServiceImpl implements CartService{


    @Autowired
    private CartMapper cartMapper;
    

   @Override
   public int add(String userId, String cId,String quantity){
        Map map1 = cartMapper.queryByCid(cId, userId);
        if (map1 != null ) {
            return  cartMapper.doCount(userId,cId,Integer.parseInt(map1.get("count").toString()) + Integer.parseInt(quantity));
        } else {
            return cartMapper.doUpdate(userId,cId, quantity);
        }

    }
    @Override
    public List<Map<String, Object>> queryAll(String uId) {
        return cartMapper.queryAll(uId);

    }

    @Override
    public int deleteCart(String userId, String cid) {
        return cartMapper.doDelete(userId,cid);
    }

    @Override
    public int deleteAll(String userId) {
        return cartMapper.doDeleteAll(userId);
    }

    @Override
    public int doadd(String id, String cid, String count) {

        return cartMapper.doCount(id,cid,Integer.parseInt(count));

    }

    @Override
    public Map<String, Object> querySelect(String uid, String cid) {
        return cartMapper.queryByCid(cid,uid);
    }

    @Override
    public int setType(String uid, String cid,int i) {
        return cartMapper.setType(uid,cid,i);
    }

    @Override
    public List<Map<String, Object>> querySelect(String uid) {
        return cartMapper.querySelect(uid);
    }

    @Override
    public int deleteSelect(String id) {
        return cartMapper.deleteSelete(id);
    }


}
