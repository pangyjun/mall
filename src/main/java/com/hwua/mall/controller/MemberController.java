package com.hwua.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.mall.po.Member;
import com.hwua.mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/regAjax",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String regAjax(String username,String tel){
        JSONObject jsonObject = new JSONObject();
        System.out.println("tel = " + tel);
        System.out.println("username = " + username);
        Member member = memberService.queryByTel(tel);
        Member member1 = memberService.queryByName(username);
        if(member1 != null){
            jsonObject.put("username","用户名已存在");
        }else if(member != null ){
            jsonObject.put("tel","电话已存在");
        }else{
            jsonObject.put("msg","");
        }
      return jsonObject.toJSONString();
    }


    @RequestMapping(value = "/regist",produces = "application/json;charset=UTF-8")
    private String regist(Member member,HttpServletRequest request){

        String password = member.getPassword();
        if(password != null){
            password = DigestUtils.md5DigestAsHex(password.getBytes());
        }
        member.setPassword(password);
        System.out.println("member = " + member);

        Member user = (Member) request.getSession().getAttribute("user");

        if( user != null){
            int userId = user.getId();
            member.setId(userId);
            int info = memberService.changeInfo(member);
            if(info > 0){
                request.setAttribute("success","**  修改信息成功！ **");
                request.getSession().setAttribute("user",member);

            }else{
                request.setAttribute("fail","修改信息失败");
            }
            return "account";
        }else{
            int regist = memberService.regist(member);
            if(regist > 0){
                //注册成功
                Member username = memberService.queryByName( member.getUsername());
                System.out.println("username = " + username);
                HttpSession session = request.getSession();
                session.setAttribute("user",username);

                return "index";

            }else{
                //注册失败
                request.setAttribute("fail","注册失败");
                return "account";
            }

        }
    }

    @RequestMapping("/logoff")
    private String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/loginAjax",produces = "application/json;charset=UTF-8")
    public String loginAjax(String tel1,String password1,HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();


        password1 = DigestUtils.md5DigestAsHex(password1.getBytes());
        Map<String, Object> login = memberService.login(tel1, password1);


        if(login.get("error") != null){
            jsonObject.put("error",login.get("error"));
        }else{
            request.getSession().setAttribute("user",login.get("success"));
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/toPassword")
    private String password(){
        return "password";
    }

    @ResponseBody
    @RequestMapping(value = "/passAjax",produces = "application/json;charset=UTF-8")
    private String passAjax(String username,String tel){
        JSONObject jsonObject = new JSONObject();
        Member member = memberService.queryByName(username);
        System.out.println("member = " + member);
        if(member != null ){
            String tel1 = member.getTel();
            if(tel.equals(tel1)){
                jsonObject.put("msg","信息正确");
                jsonObject.put("pass",member.getPassword());
            }else{
                jsonObject.put("msg","电话错误");
            }
        }else{
            jsonObject.put("msg","用户名不存在");
        }
       return jsonObject.toJSONString();
    }


    @RequestMapping("/updatePass")
    public String updatePass(String username,String password,HttpSession session){
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        int i = memberService.updatePass(username,password);
        Member member = memberService.queryByName(username);
        System.out.println("member ============= " + member);
        session.setAttribute("user",member);

        return "index";
    }

}
