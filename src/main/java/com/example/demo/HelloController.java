package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Memo;
import com.example.demo.entity.Users;
import com.example.demo.mapper.MemoMapper;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.service.UserInfoService;

@Controller
public class HelloController {

    @Autowired
    MemoMapper memoMapper;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/hello")
    public String sayHello(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        List<Memo> memos = memoMapper.findAll();
        List<Users> users = usersMapper.findAll();
        System.out.println(users);
        userInfoService.getAuthenticatedUserDetails();

        return "hello";
    }
}
