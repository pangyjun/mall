package com.hwua.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {
    @RequestMapping("/admin-role-add")
    public String admin_role_add(){
        return "/admin-role-add";
    }
}
