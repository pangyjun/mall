package com.hwua.mall.service.impl;

import com.hwua.commom.dao.AuthMapper;
import com.hwua.commom.dao.UserMapper;
import com.hwua.commom.po.Auth;
import com.hwua.commom.po.User;
import com.hwua.mall.service.AuthService;
import com.hwua.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("authService")
public class AuthServiceImpl implements AuthService{

    @Autowired
private AuthMapper authMapper;
    @Override
    public List<Auth> queryAllAuth() {
        return authMapper.queryAllAuth();
    }
}
