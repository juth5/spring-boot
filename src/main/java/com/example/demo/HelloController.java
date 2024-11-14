package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Memo;
import com.example.demo.entity.Users;
import com.example.demo.mapper.MemoMapper;
import com.example.demo.mapper.UsersMapper;

@Controller
public class HelloController {

    @Autowired
    MemoMapper memoMapper;
    @Autowired
    UsersMapper usersMapper;

    @GetMapping("/hello")
    public String sayHello() {

        List<Memo> memos = memoMapper.findAll();
        List<Users> users = usersMapper.findAll();
        System.out.println(users);

        return "hello";
    }

}
