package com.hwua.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/comment")
public class CommentController {

    @RequestMapping("feedback-list")
    public String feedback_list(){
      return "feedback-list";
    }
}
