package com.hwua.commom.dao;


import com.hwua.commom.po.Member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {
    public Member queryByTel(String tel);
    public Member queryUser(String tel, String password);

    Member queryByName(String username);

    int doUpdate(Member member);

    int changeInfo(Member member);


    int updatePass(String username, String password);

    List<Member> queryAll();

    int updateMemberFlag(Integer id,Integer i);

    int updateMemberdel(Integer id);

    Map queryByid(Integer id);
}
