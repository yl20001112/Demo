package com.cssl.controller;

import com.cssl.client.UsersClient;
import com.cssl.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    private UsersClient usersClient;

    @PostMapping("/load")
    public String load(){
        for (int i=0; i<10; i++){
            String r = usersClient.load();
            System.out.println("client:"+r);
        }
        return "load";
    }

    @PostMapping("/findMap")
    public Map<String,String> findMap(@RequestParam Map<String,String> map){
        System.out.println("cosumer findMap:"+map);
        usersClient.getMap(map);
        return map;
    }

    @PostMapping("/findById")
    public Users findUsers(Users user, HttpSession session){
        System.out.println("consumer findUsers:"+user.getId());
        String value = (String)session.getAttribute("user");
        if(value==null){
            session.setAttribute("user","user:"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:"+session.getId());
        System.out.println("consumer session value:"+value);
        return usersClient.findById(user);
    }

    @PostMapping("/login")
    public String mylogin(@RequestHeader(name="token") String token, String username, String password,HttpSession session){
        System.out.println("consumer mylogin:"+token+"\t"+username);

        String value = (String)session.getAttribute("user");
        if(value==null){
            session.setAttribute("user","user:"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:"+session.getId());
        System.out.println("consumer session value:"+value);

        return usersClient.login(token,username,password);
    }

}
