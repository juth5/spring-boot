package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Memo;
import com.example.demo.mapper.MemoMapper;

@RestController
public class HelloController {
	@Autowired
	MemoMapper memoMapper;

    @GetMapping("/hello")
    public String sayHello() {
    List<Memo> memos = memoMapper.findAll();
    System.out.println(memos);
        return "Hello, World!";
    }
}
