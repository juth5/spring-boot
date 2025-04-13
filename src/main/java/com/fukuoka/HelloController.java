package com.fukuoka;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fukuoka.entity.Memo;
import com.fukuoka.entity.Users;
import com.fukuoka.mapper.MemoMapper;
import com.fukuoka.mapper.UsersMapper;
import com.fukuoka.service.UserInfoService;

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

        userInfoService.getAuthenticatedUserDetails();

        return "hello";
    }
}
