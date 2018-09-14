package com.example.seckill.controller;

import com.example.seckill.entities.SeckillUser;
import com.example.seckill.redis.RedisService;
import com.example.seckill.service.SeckillUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String toList(Model model, SeckillUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }
}
