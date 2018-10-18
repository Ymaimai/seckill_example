package com.example.seckill.controller;

import com.example.seckill.entities.OrderInfo;
import com.example.seckill.entities.SeckillOrder;
import com.example.seckill.entities.SeckillUser;
import com.example.seckill.result.CodeMsg;
import com.example.seckill.service.GoodsService;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.SeckillService;
import com.example.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SeckillService seckillService;

    @RequestMapping("/do_seckill")
    public String seckill(Model model, SeckillUser user, @RequestParam("goodsId") Integer goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";//未登陆时，返回登陆页面
        }

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);//10个商品，req1 req2
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        //判断是否已经秒杀到了
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {//该用户已秒杀成功
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "seckill_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = seckillService.seckill(user, goods);//返回生成订单
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
