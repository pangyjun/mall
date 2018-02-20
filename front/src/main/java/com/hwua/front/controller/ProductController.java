package com.hwua.front.controller;

import com.alibaba.fastjson.JSON;

import com.hwua.commom.po.Member;
import com.hwua.commom.po.Product;
import com.hwua.front.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    public String products(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id1 = request.getParameter("id1");
        String id2 = request.getParameter("id2");
        String id3 = request.getParameter("id3");
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        String search = request.getParameter("search");
        String value = request.getParameter("value");
        id1 = "".equals(id1)?null:id1;
        id2 = "".equals(id2)?null:id2;
        id3 = "".equals(id3)?null:id3;
        id = "".equals(id)?null:id;
        search = "".equals(search)?null:search;
        value = "".equals(value)?null:value;


        List<Product> list = null;
        int size = 0;
        int i = 0;
        Integer val;
        if(page == null || "".equals(page)){ i= 1;}else{i = Integer.parseInt(page);}
        if(value == null || "".equals(value)){ val = null;}else{val = Integer.parseInt(value);}
       /* if(search != null){
            list = productService.rearch(search);
            size = list.size();
        }*/
        HashMap<String, Object> map = new HashMap<>();
        map.put("id1",id1);
        map.put("id2",id2);
        map.put("id3",id3);
        map.put("id",id);
        map.put("search",search);
        map.put("start",18*(i-1));
        map.put("value",value);
        list =  productService.query(map);
        size= productService.queryCount(map);
        if(size%18==0){
            size=size/18;
        }else{
            size=size/18+1;
        }
        request.setAttribute("id1",id1);
        request.setAttribute("id2",id2);
        request.setAttribute("id3",id3);
        request.setAttribute("id",id);
        request.setAttribute("search",search);
        request.setAttribute("value",value);


      /*  List<Product> list = null;
        int size = 0;
        if(page != null){
            size = productService.queryAll().size();
            int i = Integer.parseInt(page);

             list = productService.queryAllLimit((i - 1) * 18);
        } if(value != null){
            System.out.println("value ---------------= " + value);
            if("1".equals(value)){
                list = productService.queryOrder1(1);
            } if("2".equals(value)){
                list = productService.queryOrder2(2);
            } if("3".equals(value)){
                list = productService.queryOrder3(3);
            }
             size = list.size();

        }else if( id != ""){
            size = productService.queryAll().size();
            //分页查询
            list = productService.queryAllLimit(1);

        }else if (search != null ){

            list = productService.rearch(search);
            size = list.size();
        }else if(id1 != null && id2 != null && id3 != null){
            list = productService.queryByCategory(id1, id2, id3);
            size = list.size();
        }else if("3".equals(id1)){
            list = productService.queryCupCake();
            size = list.size();
        }*/

        request.setAttribute("cakes",list);
        request.setAttribute("size",size);

        return "products";
    }

    @RequestMapping("/history")
   private String history(HttpServletRequest request){
        int i = 0;
        Cookie[] cookies = request.getCookies();
        ArrayList<Product> list = new ArrayList<>();
        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("user");
        Arrays.sort(cookies, new Comparator<Cookie>() {
            @Override
            public int compare(Cookie o1, Cookie o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        if(user != null){
            String id = user.getId().toString();
            for (Cookie c : cookies) {
                if(c.getName().contains("-") && c.getName().startsWith(id) ){
                    i++;
                    if(i<=8){
                        String[] split = c.getName().split("-");
                        String s = split[1];
                        String cid = c.getValue();
                        Product product = productService.queryById(s);
                        list.add(product);
                    }else {
                        break;
                    }
                }
            }
        }
        System.out.println(JSON.toJSONString(list));
        request.setAttribute("list",list);
        return "index";
    }

    @RequestMapping("/toindex")
    private String index(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        List<Product> maps = productService.queryNewCake2_8();
        Product map1 = productService.queryNewCake1();
        Product map2 = productService.queryNewCake2();
        servletContext.setAttribute("new8_10",maps);
        servletContext.setAttribute("one",map1);
        servletContext.setAttribute("two",map2);

        HttpSession session = request.getSession();

        if(session.getAttribute("user") != null){
           return "redirect:/history";
       }else{
            return "index";
        }
    }


    @RequestMapping("/single")
    private String single(Map map ,HttpServletRequest request,HttpServletResponse response){


        String id = request.getParameter("id");
        List<Map> list = productService.queryComm(id);
        map.put("list",list);
        Product product = productService.queryById(id);
        List<Product> list1 = productService.querySame(id);
        request.setAttribute("cake",product);
        /*cakeBiz.querySame();*/
        Member user = (Member) request.getSession().getAttribute("user");
        if(user != null){
            String uid = user.getId().toString();
            Cookie c2 = new Cookie(uid+"-"+id, new Date().getTime()+"");
            //设置cookie的有效期
            c2.setMaxAge(1200);
            response.addCookie(c2);
        }
        return "single";
    }



}
