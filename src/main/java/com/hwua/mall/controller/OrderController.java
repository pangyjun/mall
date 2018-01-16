package com.hwua.mall.controller;

import com.hwua.mall.po.Member;
import com.hwua.mall.po.Product;
import com.hwua.mall.service.CartService;
import com.hwua.mall.service.OrderService;
import com.hwua.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
   @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

  /*  @RequestMapping("order_list")
    private String order_list(HttpServletRequest request){
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

        request.setAttribute("nopay",maps);
        request.setAttribute("nosend",maps1);
        request.setAttribute("noreceive",maps2);
        request.setAttribute("noconfirm",maps3);
        request.setAttribute("success",maps4);
        return "order";


    }
*/

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
          map.put("pid",product.getId());
          map.put("count",count);
          i=Integer.parseInt(count)*product.getPrice();
          maps.add(map);
          request.setAttribute("single","single");
      }else{
          maps = cartService.querySelect(user.getId().toString());
          for (Map map : maps) {
              i = i + Integer.parseInt(map.get("price").toString())*Integer.parseInt(map.get("count").toString());
          }
          request.setAttribute("single","");
      }
      request.setAttribute("list",maps);
      request.setAttribute("money",i);

      return "order_info";


  }


}
