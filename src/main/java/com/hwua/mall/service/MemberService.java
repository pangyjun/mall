package com.hwua.mall.service;

import com.hwua.mall.po.Member;

import java.util.Map;

public interface MemberService {
    Map<String,Object> login(String tel, String password);
    int regist(Member member);
    Member queryByName(String username);
    Member queryByTel(String tel);

    int changeInfo(Member member);

    int updatePass(String username, String password);
}
