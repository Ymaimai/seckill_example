package com.example.seckill.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.example.seckill.dao.GoodsDao;
import com.example.seckill.entities.Goods;
import com.example.seckill.entities.OrderInfo;
import com.example.seckill.entities.SeckillOrder;
import com.example.seckill.entities.SeckillUser;
import com.example.seckill.redis.RedisService;
import com.example.seckill.util.MD5Util;
import com.example.seckill.util.UUIDUtil;
import com.example.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("restriction")
@Service
public class SeckillService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @Transactional
    public OrderInfo seckill(SeckillUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        OrderInfo orderInfo = orderService.createOrder(user, goods);
        return orderInfo;
    }

}
