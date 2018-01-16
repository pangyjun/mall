package com.hwua.mall.service.impl;

import com.hwua.mall.dao.OrderMapper;
import com.hwua.mall.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
}
