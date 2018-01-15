package com.hwua.mall.dao;

import com.hwua.mall.po.Member;



public interface MemberMapper {
    public Member queryByTel(String tel);
    public Member queryUser( String tel , String password);

    Member queryByName(String username);

    int doUpdate(Member member);

    int changeInfo(Member member);


    int updatePass(String username, String password);
}
