package com.example.seckill.service;

import java.math.BigInteger;
import java.util.Date;

import com.example.seckill.dao.OrderDao;
import com.example.seckill.entities.OrderInfo;
import com.example.seckill.entities.SeckillOrder;
import com.example.seckill.entities.SeckillUser;
import com.example.seckill.redis.OrderKey;
import com.example.seckill.redis.RedisService;
import com.example.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    RedisService redisService;

    public SeckillOrder getSeckillOrderByUserIdGoodsId(BigInteger userId, Integer goodsId) {
        return orderDao.getSeckillOrderByUserIdGoodsId(userId, goodsId);
    }

    public OrderInfo getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Transactional
    public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {//生成订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(1001);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(seckillOrder);
        return orderInfo;
    }

    public void deleteOrders() {
        orderDao.deleteOrders();
        orderDao.deleteSeckillOrders();
    }

}
