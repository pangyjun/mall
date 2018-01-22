package com.hwua.front.service.impl;


import com.hwua.commom.dao.MemberMapper;
import com.hwua.commom.po.Member;
import com.hwua.front.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Map<String, Object> login(String tel, String password) {
        Member member = memberMapper.queryUser(tel, password);
        Map map = new HashMap<String, Object>();
        if (member == null){

            Member member1 = memberMapper.queryByTel(tel);
            if(member1 == null){
                map.put("error","用户不存在");
                return map;
            }else{
                map.put("error","密码错误");
                return map;
            }
        }else{
            map.put("success",member);
        }
        return map;
    }

    @Override
    public Member queryByName(String username) {
        return memberMapper.queryByName(username);
    }

    @Override
    public Member queryByTel(String tel) {
        return memberMapper.queryByTel(tel);
    }

    @Override
    public int changeInfo(Member member) {
        System.out.println("member = " + member);
        return memberMapper.changeInfo(member);
    }

    @Override
    public int updatePass(String username, String password) {
        return memberMapper.updatePass(username,password);
    }


    @Override
    public int regist(Member member) {
        return memberMapper.doUpdate(member);
    }

}
