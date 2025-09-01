package com.fukuoka.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fukuoka.entity.Account;

@Mapper
public interface AccountMapper {
  @Select("SELECT * FROM account")
  List<Account> findAll();

  @Select("SELECT * FROM account WHERE username = #{name}")
  Account findByUsername(@Param("name") String name);

  @Insert("INSERT INTO account(username, password) VALUES(#{username}, #{password})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(Account account);
  
}
