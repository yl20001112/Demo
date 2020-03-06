package com.example.controller;

import com.cssl.pojo.Users;
import com.example.service.UsersService;
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
    public void setProt(String port){
        System.out.println("set:"+port);
        this.port=port;
    }

    @RequestMapping("/load")
    public String load() throws InterruptedException {

        System.out.println("prot:"+port);
        if(port.equals("7002")){
            Thread.sleep(500);
        }
        return "load:"+port;
    }

    @Autowired
    private UsersService usersService;

    //出异常区回调方法避免返回异常; ------熔断的手段;
    @HystrixCommand(fallbackMethod = "hystrixfallback")
    @RequestMapping("/select")
    public Users select(@RequestBody Users users, HttpSession session){
        System.out.println("查询:"+users.getId());
        String ses = (String)session.getAttribute("user");
        if(ses == null){
            session.setAttribute("user","user"+System.currentTimeMillis());
        }
        System.out.println("consumer sessionId:" + session.getId());
        System.out.println("consumer session value:" + ses);
        Users users1=usersService.select(users);
        if(users1==null){
            throw new RuntimeException("对象不存在!");
        }
        return users1;
    }

    /**
     * 回调方法参数和原方法一致;
     * @param users
     * @return
     */
    public Users hystrixfallback(Users users, HttpSession session){
        System.out.println("callback:" + users);
        return new Users(0,"用户为空","不存在");
    }
    @RequestMapping("/login")
    public String login(@RequestHeader(name = "token") String token, String name, String pwd){
        System.out.println("name:"+name+",pwd:"+pwd+";");
        if(usersService.login(token,name,pwd)>0){
            return "成功!!!!";
        }
        return "失败!!!";
    }
    @RequestMapping("/selMap")
    public Map<String, String> selMap(@RequestParam Map<String, String> map) {
        System.out.println("map:"+map);
        return usersService.selMap(map);
    }

}
