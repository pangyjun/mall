package com.hwua.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
public class MemberController {

    @RequestMapping("/member-list")
    public String member_list(){
        return "member-list";
    }


    @RequestMapping("/member-del")
    public String member_del(){
        return "member-del";
    }


    @RequestMapping("/member-record-download")
    public String member_record_download(){
        return "member-record-download";
    }
}
