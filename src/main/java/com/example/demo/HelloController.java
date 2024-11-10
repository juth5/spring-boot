package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Announce;
import com.example.demo.entity.Memo;
import com.example.demo.mapper.AnnounceMapper;
import com.example.demo.mapper.MemoMapper;

@Controller
public class HelloController {
	@Autowired
	MemoMapper memoMapper;

    @Autowired
    AnnounceMapper announceMapper;

    @GetMapping("/hello")
    public String sayHello() {
        //List<Announce> announces = announceMapper.findAll();



        return "hello";
    }
}
