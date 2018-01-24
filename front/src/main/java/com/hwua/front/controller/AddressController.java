package com.hwua.front.controller;

import com.hwua.commom.po.Address;
import com.hwua.commom.po.Member;
import com.hwua.front.service.AddressService;
import com.hwua.front.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/del")
    public String delete(Integer id, HttpSession session){
        Member user = (Member) session.getAttribute("user");
        Integer mid = user.getId();
        int i =  addressService.delete(id,mid);
        return "redirect:/address_list";
    }

    @RequestMapping("/edit")
    public String edit(Address address,HttpSession session){
        Member user = (Member) session.getAttribute("user");
        address.setMid(user.getId());
        System.out.println("address````````````````````````` = " + address);
        if(address.getId() != null){
            if(address.getFlag()==1){
                addressService.updateFlag(user.getId());
            }


            addressService.doupdate(address);
        }else{

            System.out.println("address = " + address);
            addressService.doInsert(address);
        }


        return "redirect:/address_list";
    }
}
