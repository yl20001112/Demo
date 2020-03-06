package com.example.controller;

import com.example.client.UsersClient;
import com.cssl.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class UsersController {
    @Autowired
    private UsersClient usersClient;

    @RequestMapping("/load")
    public String load(){

        for (int i = 0; i < 10; i++){
            String sum=usersClient.load();
            System.out.println("sum:"+sum);
        }
        return "load";
    }

    @RequestMapping("/login")
    public String login(@RequestHeader(name = "token") String token, String name, String pwd){
        System.out.println("mylogin:"+name);
        return usersClient.login(token,name,pwd);
    }

    @RequestMapping("/select")
    public Users select(Users users, HttpSession session){
        System.out.println("users:"+users.getId());
        String ses = (String)session.getAttribute("user");
        if(ses == null){
            session.setAttribute("user","user"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:" + session.getId());
        System.out.println("consumer session value:" + ses);
        return usersClient.select(users);
    }

    @RequestMapping("/selMap")
    public Map<String,String> selMap(@RequestParam Map<String,String> map){
        System.out.println("map:"+map);
        return usersClient.selMap(map);
    }


}
