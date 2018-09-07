package com.example.seckill.dao;

import com.example.seckill.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from users where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into users(id, name)values(#{id}, #{name})")
    public int insert(User user);

}
