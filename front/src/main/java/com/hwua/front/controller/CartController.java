package com.hwua.front.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.hwua.commom.po.Member;
import com.hwua.commom.po.Product;
import com.hwua.front.service.CartService;
import com.hwua.front.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/productAdd", produces = "application/json;charset=UTF-8")
    private String productAdd(HttpServletRequest request, String cid1) throws IOException {

        JSONObject jsonObject = new JSONObject();

        Member u = (Member) request.getSession().getAttribute("user");
        System.out.println(JSON.toJSONString(u));
        if (u != null) {
            String userId = u.getId().toString();
//            String cid1 = request.getParameter("cid1");
            System.out.println("cid1 ==================== " + cid1);
            int id = cartService.add(userId, cid1, "1");
            if (id > 0) {
                jsonObject.put("msg", "添加成功");
            } else {
                jsonObject.put("msg", "添加失败");
            }
        } else {
            jsonObject.put("msg", "请先登录!");

        }
        System.out.println("jsonObject = " + jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    @RequestMapping("/cart")
    private String cart(HttpServletRequest request) {
        Member user = (Member) request.getSession().getAttribute("user");
        List<Map<String, Object>> maps = cartService.queryAll(user.getId().toString());
        for (Map map : maps) {
            System.out.println("map = " + map.get("pid").toString());
            int i = cartService.setType(user.getId().toString(), map.get("pid").toString(), 0);
            System.out.println("i = ===================" + i);
        }
        System.out.println("maps = " + maps);
        request.setAttribute("carts", maps);
        return "checkout";
    }

    @ResponseBody
    @RequestMapping(value = "/doAddCart", produces = "application/json;charset=UTF-8")
    public String doAddCart(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("user");

        String user2 = request.getParameter("user");
        if (user != null) {
            String userId = user.getId().toString();
            String quantity = request.getParameter("quantity");
            String user1 = request.getParameter("user");
            String text = request.getParameter("text");
            System.out.println("text = " + text);
            String cid = request.getParameter("cid");
            if ("加入购物车".equals(text)) {
                jsonObject.put("msg", "加入购物车成功（" + quantity + "个）");
                //加入购物车
                int id = cartService.add(userId, cid, quantity);
                if (id > 0) {
                    //System.out.println("添加成功");
                } else {
                    //System.out.println("添加失败");
                }
            } else if ("立即购买".equals(text)) {
                //购买 --》 订单
                jsonObject.put("msg", "");

            }
        } else if (user2 != null) {
            String userId = user.getId().toString();
            String cid1 = request.getParameter("cid1");
            System.out.println("cid1 = " + cid1);
            int id = cartService.add(userId, cid1, "1");
            if (id > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");

            }
        }
        System.out.println("jsonObject---------------- = " + jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/totle", produces = "application/json;charset=UTF-8")
    public String totle(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        HttpSession session = request.getSession();

        Member user = (Member) session.getAttribute("user");
        String uid = user.getId().toString();
        String cid = request.getParameter("cid");
        String checked = request.getParameter("checked");
        String t_checked = request.getParameter("t_checked");
        Double totle = 0.0;
        List<Map<String, Object>> maps = cartService.queryAll(uid);
        System.out.println("maps =----------" + maps);

        if (!("null".equals(checked))) {
            int i = 0;
            if ("true".equals(checked)) {
                i = cartService.setType(uid, cid, 1);
            } else {
                i = cartService.setType(uid, cid, 0);
            }

            if (i == 1) {
                //设置成功
            } else {
                //设置失败
            }
            List<Map<String, Object>> maps1 = cartService.querySelect(uid);
            System.out.println("maps1 = " + maps1);

            for (Map map : maps1) {
                totle = totle + Double.parseDouble(map.get("price").toString()) * Integer.parseInt(map.get("count").toString());
            }
        } else if (t_checked.equals("true")) {


            for (Map map : maps) {
                cartService.setType(uid, map.get("pid").toString(), 1);
                totle = totle + Double.parseDouble(map.get("price").toString()) * Integer.parseInt(map.get("count").toString());
            }
        } else if (t_checked.equals("false")) {
            for (Map map : maps) {
                cartService.setType(uid, map.get("cid").toString(), 0);
            }
            totle = 0.0;
        }


        jsonObject.put("msg", totle);
        System.out.println("jsonObject = " + jsonObject.toJSONString());
        return jsonObject.toJSONString();

    }

    @ResponseBody
    @RequestMapping(value = "/tot", produces = "application/json;charset=UTF-8")
    public String tot(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("user");
        String uid = user.getId().toString();
        String t_checked = request.getParameter("t_checked");
        double totle = 0.0;
        List<Map<String, Object>> maps = cartService.queryAll(uid);

        if (t_checked.equals("true")) {
            for (Map map : maps) {
                cartService.setType(uid, map.get("pid").toString(), 1);
                totle = totle + Double.parseDouble(map.get("price").toString()) * Integer.parseInt(map.get("count").toString());
            }
        } else {
            for (Map map : maps) {
                cartService.setType(uid, map.get("pid").toString(), 0);
            }
            totle = 0.0;
        }


        jsonObject.put("msg", totle);
        System.out.println("jsonObject = " + jsonObject.toJSONString());
        return jsonObject.toJSONString();

    }

    @ResponseBody
    @RequestMapping(value = "/doCart", produces = "application/json;charset=UTF-8")
    public String doCart(HttpServletRequest request) {
        System.out.println(" ********************");

        JSONObject jsonObject = new JSONObject();

        String count = request.getParameter("count");
        int i = Integer.parseInt(count);
        System.out.println("i = " + i);

        String cid = request.getParameter("cid");
        Product product = productService.queryById(cid);
        Double i1 = product.getPrice();

        System.out.println("i1 = " + i1);
        Member user = (Member) request.getSession().getAttribute("user");
        String userid = user.getId().toString();
        int add = cartService.doadd(userid, cid, count);
        System.out.println("add = " + add);
        if (add > 0) {
            //System.out.println("修改成功");
        } else {
            //System.out.println("修改失败");
        }
        Double money = i * i1;
        jsonObject.put("msg", money);

        List<Map<String, Object>> maps1 = cartService.querySelect(userid);
        double totle = 0.0;
        for (Map map : maps1) {
            totle = totle + Double.parseDouble(map.get("price").toString()) * Integer.parseInt(map.get("count").toString());
        }
        jsonObject.put("totle", totle);
        System.out.println("jsonObject = " + jsonObject.toJSONString());
        return jsonObject.toJSONString();

    }

    @RequestMapping("/deleteCart")
    private String deleteCart(HttpServletRequest request, String cid) {
        Member user = (Member) request.getSession().getAttribute("user");
        String userId = user.getId().toString();
        System.out.println("\"************\" = " + "************");
        if (cid == null) {
            //清空购物车
            System.out.println("----------");
            int i = cartService.deleteAll(userId);
            if (i > 0) {
                System.out.println("清空购物车成功");
            } else {
                System.out.println("清空购物车失败");
            }


        } else {
            //删除单个商品
            int i = cartService.deleteCart(userId, cid);
            if (i > 0) {
               /* System.out.println("删除成功");*/
                return "redirect:/cart";
            } else {
                System.out.println("删除失败");
            }
        }
        return "";
    }


    @RequestMapping(value = "/empty", produces = "application/json;charset=UTF-8")
    public String empty() {
        return "redirect:/deleteCart";
    }



}




