package com.fukuoka.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fukuoka.entity.Users;

@Mapper
public interface UsersMapper {
  @Select("SELECT * FROM users")
  List<Users> findAll();

  @Select("SELECT * FROM users WHERE name = #{name}")
  Users findByUsername(String name);
  
}
