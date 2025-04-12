package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.CustomUserDetails;
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
        //sessionの中にユーザー情報が入っている
        //Controllerには自動で入ってくる
        HttpSession session = request.getSession(false);
        List<Memo> memos = memoMapper.findAll();
        List<Users> users = usersMapper.findAll();

        CustomUserDetails user = userInfoService.getAuthenticatedUserDetails();
        System.out.println("aaaaaaaaaaa");

        System.out.println(user.getEmail());
        System.out.println("aaaaaaaaaaa");

        return "hello";
    }
}
