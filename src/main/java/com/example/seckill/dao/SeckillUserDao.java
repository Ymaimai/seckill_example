package com.example.seckill.dao;

import com.example.seckill.entities.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;

@Mapper
public interface SeckillUserDao {
    @Select("select * from seckill_user where id = #{id}")
    public SeckillUser getById(@Param("id") BigInteger id);

    @Update("update seckill_user set password = #{password} where id = #{id}")
    public void update(SeckillUser toBeUpdate);
}
