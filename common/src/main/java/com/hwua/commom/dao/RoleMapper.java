package com.hwua.commom.dao;

import com.hwua.commom.po.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    List<Map> queryAllRole();
    List<Map> queryRoleofUser(Object roleId);

}
