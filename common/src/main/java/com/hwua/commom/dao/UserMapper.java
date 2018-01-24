package com.hwua.commom.dao;

import com.hwua.commom.po.User;

import java.util.Map;

public interface UserMapper {
    public Map<String, String> queryByName(String userName) ;

    User queryUser(String userName, String pass);
}
