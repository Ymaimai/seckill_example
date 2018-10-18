package com.example.seckill.controller;

import com.example.seckill.entities.SeckillUser;
import com.example.seckill.redis.RedisService;
import com.example.seckill.service.GoodsService;
import com.example.seckill.service.SeckillUserService;
import com.example.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SeckillUserService userService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model, SeckillUser user) {
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, SeckillUser user, @PathVariable("goodsId") Integer goodsId) {
        model.addAttribute("user", user);//将封装的user传递过来

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);//传入到页面
        //获取秒杀的开始时间和结束时间及当前时间
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;//状态
        int remainSeconds = 0;
        if (now < startAt) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);//参数传入到页面
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
