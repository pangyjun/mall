package com.hwua.front.controller;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.commom.po.*;
import com.hwua.front.service.AddressService;
import com.hwua.front.service.CartService;
import com.hwua.front.service.OrderService;
import com.hwua.front.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @RequestMapping("order_list")
    public String order_list(HttpServletRequest request){
        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("user");
        String id = user.getId().toString();
        String orderid = request.getParameter("orderid");

        if(orderid != null){
            int del = orderService.del(id, orderid);
            if(del >0 ){
                //
                System.out.println("成功");
            }else{
                System.out.println("失败");
           }

        }

        List<Map<String, Object>> maps = orderService.queryNoPay(id);//代付款
        List<Map<String, Object>> maps1 = orderService.queryHavePay(id);//代发货
        List<Map<String, Object>> maps2 = orderService.querySend(id);//待收货
        List<Map<String, Object>> maps3 = orderService.queryNoConfirm(id);//待评价
        List<Map<String, Object>> maps4 = orderService.querySuccess(id);//交易成功
//        List<Map<String,Object>> all = new ArrayList<>();
//        all.addAll(maps);
//        all.addAll(maps1);
//        all.addAll(maps2);
//        all.addAll(maps3);
//        all.addAll(maps4);

        System.out.println("代付款 ======== " + JSON.toJSONString(maps));
        System.out.println("代发货 ======== " + JSON.toJSONString(maps1));
        System.out.println("待收货 ======== " + JSON.toJSONString(maps2));
        System.out.println("待评价 ======== " + JSON.toJSONString(maps3));
        System.out.println("交易成功 ======== " + JSON.toJSONString(maps4));

        request.setAttribute("nopay",maps);
        request.setAttribute("nosend",maps1);
        request.setAttribute("noreceive",maps2);
        request.setAttribute("noconfirm",maps3);
        request.setAttribute("success",maps4);
//        request.setAttribute("all",all);
        return "order";


    }

    @RequestMapping("/commentList")
    public String comment(String orderId,Map map){
        List<Map<String, Object>> list = orderService.queryByOrderId(orderId);//待评价
        map.put("list",list);
        map.put("orderId",orderId);
        System.out.println("=========================list = " + JSON.toJSONString(list));
        return "comment";

    }


  @RequestMapping("/do_order")
  public String do_order(HttpServletRequest request){
      Member user = (Member) request.getSession().getAttribute("user");
      List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
      String cid = request.getParameter("cakeid");
      System.out.println("cid = " + cid);
      String count = request.getParameter("quantity");
      double i = 0.0;
      if(cid != null){
          Product product =  productService.queryById(cid);
          HashMap<String, Object> map = new HashMap<>();
          map.put("price",product.getPrice());
          map.put("id",product.getId());
          map.put("name",product.getName());
          map.put("imgs",product.getImgs());
          map.put("count",count);
          i=Integer.parseInt(count)*product.getPrice();
          maps.add(map);
          request.setAttribute("single","single");
      }else{
          maps = cartService.querySelect(user.getId().toString());
          for (Map map : maps) {
              i = i + Double.parseDouble(map.get("price").toString())*Integer.parseInt(map.get("count").toString());
          }
          request.setAttribute("single","");
      }
      request.setAttribute("list",maps);
      request.setAttribute("money",i);
      return "order_info";
  }


  @RequestMapping("/sure")
  public String sure(HttpServletRequest request){

      String username = request.getParameter("username");
      String tel = request.getParameter("tel");
      String address = request.getParameter("address");
      String money = request.getParameter("money");
      HashMap<String, Object> map = new HashMap<String, Object>();
      Member user = (Member) request.getSession().getAttribute("user");
//      收件人地址address表

      Address address1 = new Address();
      address1.setAddr(address);
      address1.setTel(tel);
      address1.setMid(user.getId());
      address1.setName(username);
      int i = addressService.doInsert(address1);
      System.out.println("address1 = " + address1.getId());

      String single = request.getParameter("single");
    /*  map.put("mid", user.getId());
      map.put("tot_money", money);
      map.put("aid",address1.getId());
*/
      Orders orders = new Orders();
      orders.setAid(address1.getId());
      orders.setTot_money(Double.parseDouble(money));
      orders.setMid(user.getId());
      int add = orderService.add(orders);
      if(add >0){
          System.out.println("添加成功");
          Map<String, Object> map1 = new HashMap<>();
          int s = orders.getId();
          if(single.contains("sin")){
              String price = request.getParameter("price");
              String count = request.getParameter("count");
              String cid = request.getParameter("cid");
              System.out.println("cid_---------------------- = " + cid);
              OrderDetail orderDetail = new OrderDetail();
              orderDetail.setCount(Integer.parseInt(count));
              orderDetail.setOid(s);
              orderDetail.setPid(Integer.parseInt(cid));
              orderDetail.setPrice(Double.parseDouble(price));
              int i1 = orderService.addDetail(orderDetail);
          }else{
              List<Map<String, Object>> maps = cartService.querySelect(user.getId().toString());
              List<OrderDetail> list= new ArrayList<>();
              for (Map m:maps) {
                  OrderDetail o = new OrderDetail();

                  o.setPrice(Double.parseDouble(m.get("price").toString()));
                  o.setPid(Integer.parseInt(m.get("pid").toString()));
                  o.setOid(s);
                  o.setCount(Integer.parseInt(m.get("count").toString()));
                  list.add(o);
              }
              orderService.doAddBatch(list);
          }
          cartService.deleteSelect(user.getId().toString());

          System.out.println("s = -------------------" + s);


          request.setAttribute("oderid",s);
          request.setAttribute("tot_money",money);

          return "do_pay";
//          request.getRequestDispatcher("do_pay.jsp?oderid="+s).forward(request,response);
      }else{
          System.out.println("失败");
      }
      return  "";

  }

  @RequestMapping("/success")
  public String success(HttpServletRequest request){
//      String tot_money = request.getParameter("tot_money");

      request.setAttribute("str","order");
      request.setAttribute("oderid",request.getParameter("oderid"));

      return "result";
//      response.sendRedirect("result.jsp?str=order&oderid="+request.getParameter("oderid"));

  }


  @RequestMapping("/order")
  private String order(HttpServletRequest request){
      String oderid = request.getParameter("oderid");
      HttpSession session = request.getSession();
      Member user = (Member) session.getAttribute("user");
      String id = user.getId().toString();
      int i = orderService.setPayTime(oderid);
      if(i >0){
          System.out.println("支付完成");

          List<Map<String, Object>> maps = orderService.queryNoPay(id);//代付款
          List<Map<String, Object>> maps1 = orderService.queryHavePay(id);//代发货
          List<Map<String, Object>> maps2 = orderService.querySend(id);//待收货
          List<Map<String, Object>> maps3 = orderService.queryNoConfirm(id);//待评价
          List<Map<String, Object>> maps4 = orderService.querySuccess(id);//交易成功
          request.setAttribute("nopay",maps);
          request.setAttribute("nosend",maps1);
          request.setAttribute("noreceive",maps2);
          request.setAttribute("noconfirm",maps3);
          request.setAttribute("success",maps4);
//          request.getRequestDispatcher("order.jsp").forward(request,response);
      }else{
          System.out.println("支付失败");
      }
      return "redirect:/order_list";

  }


  @ResponseBody
  @RequestMapping(value = "/remind",produces = "application/json;charset=utf-8")
  private String remind(String orderId){
      System.out.println(orderId);
      JSONObject jsonObject = new JSONObject();
      int i = orderService.doRemind(orderId);
      jsonObject.put("msg",i>0);
    return jsonObject.toJSONString();
  }


    @ResponseBody
    @RequestMapping(value = "/order_pay",produces = "application/json;charset=utf-8")
    private String order_pay(String orderId){
        JSONObject jsonObject = new JSONObject();
        int i = orderService.doPay(orderId);
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/order_confirm",produces = "application/json;charset=utf-8")
    private String order_confirm(String orderId){
        JSONObject jsonObject = new JSONObject();
        int i = orderService.order_confirm(orderId);
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }


    @RequestMapping("/commendOk")
    private String commendOk(Comment comment){
        System.out.println("comment = " + comment);
        return "success";
    }

}
