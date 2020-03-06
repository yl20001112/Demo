package com.cssl.controller;

import com.cssl.service.UsersService;
import com.cssl.pojo.Users;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class UsersController {

    private String port;

    @Value("${server.port}")
    public void setPort(String port){
        System.out.println("setPort:"+port);
        this.port = port;
    }

    @Autowired
    private UsersService service;

    @PostMapping("/load")
    public String load() throws InterruptedException {
        System.out.println("load:"+port);
        if(port.equals("7002")){
            Thread.sleep(500);
        }
        return "load:"+port;
    }

    /**
     * @RequestParam必须要有
     * @param map
     * @return
     */
    @PostMapping("/findMap")
    public Map<String,String> findMap(@RequestParam Map<String,String> map){
        System.out.println("provider findMap:"+map);
        return map;
    }

    /**
     * @RequestBody必须要有
     * @param user
     * @return
     */
    //出异常区回调方法避免返回异常
    @HystrixCommand(fallbackMethod = "hystrixfallback")
    @PostMapping("/findById")
    public Users findById(@RequestBody Users user, HttpSession session){
        System.out.println("provider findById:"+user.getId());

        String value = (String)session.getAttribute("user");
        if(value==null){
            session.setAttribute("user","user:"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:"+session.getId());
        System.out.println("consumer session value:"+value);

        Users user2 = service.queryById(user);
        if(user2==null){
            throw new RuntimeException("对象不存在！");
        }
        return user2;
    }

    /**
     * 回调方法参数和原方法一致
     * @param user
     * @return
     */
    public Users hystrixfallback(@RequestBody Users user, HttpSession session){
        System.out.println("callback..."+user);
        return new Users(0,"用户为空","不存在");
    }

    @PostMapping("/login")
    public String login(@RequestHeader(name="token") String token, String username, String password, HttpSession session){
        System.out.println("provider login:"+token+"\t"+username);

        String value = (String)session.getAttribute("user");
        if(value==null){
            session.setAttribute("user","user:"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:"+session.getId());
        System.out.println("consumer session value:"+value);

        if(service.queryByUsername(username,password)){
            return "success";
        }
        return "input";
    }



}
