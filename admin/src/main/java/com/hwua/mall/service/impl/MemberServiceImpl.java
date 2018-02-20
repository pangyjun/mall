package com.hwua.mall.service.impl;

import com.hwua.commom.dao.MemberMapper;
import com.hwua.commom.po.Member;
import com.hwua.mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public List<Member> queryAll() {
        return memberMapper.queryAll();
    }

    @Override
    public int updateMemberFlag(Integer id) {

       Map map =  memberMapper.queryByid(id);
       if(Integer.parseInt(map.get("flag").toString()) == 0){
           return memberMapper.updateMemberFlag( id,1);
       }else{
           return memberMapper.updateMemberFlag( id,0);
       }

    }

    @Override
    public int updateMemberdel(Integer id) {
        return memberMapper.updateMemberdel(id);
    }
}
