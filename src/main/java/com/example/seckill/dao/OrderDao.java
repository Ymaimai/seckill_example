package com.example.seckill.dao;

import com.example.seckill.entities.OrderInfo;
import com.example.seckill.entities.SeckillOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.math.BigInteger;

@Mapper
public interface OrderDao {

    @Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") BigInteger userId, @Param("goodsId") Integer goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Integer.class, before = false, statement = "select order_info_seq.currval from dual")
    public Integer insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id) values (#{userId}, #{goodsId}, #{orderId})")
    public int insertSeckillOrder(SeckillOrder seckillOrder);

    @Select("select * from order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId") Integer orderId);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from miaosha_order")
    public void deleteSeckillOrders();


}