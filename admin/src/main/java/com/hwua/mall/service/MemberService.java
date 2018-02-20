package com.hwua.mall.service;

import com.hwua.commom.po.Member;

import java.util.List;

public interface MemberService {
    List<Member> queryAll();

    int updateMemberFlag(Integer id);

    int updateMemberdel(Integer id);
}
