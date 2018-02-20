package com.hwua.mall.controller;

import com.hwua.commom.po.Member;
import com.hwua.mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @RequestMapping("/member-list")
    public String member_list(Map map){

        List<Member> list = memberService.queryAll();
        map.put("list",list);
        System.out.println("list =```````````` " + list);
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


    @RequestMapping("/memeber_flag")
    public String memeber_flag(Integer id){
      int i =   memberService.updateMemberFlag(id);
        return "redirect:/member/member-list";
    }

    @RequestMapping("/member_delete")
    public String member_delte(Integer id){
        int i =   memberService.updateMemberdel(id);
        return "redirect:/member/member-list";
    }
}
