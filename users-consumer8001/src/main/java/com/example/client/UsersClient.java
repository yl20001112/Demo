package com.example.client;

import com.cssl.pojo.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//@FeignClient(name = "USERS-PROVIDER")
//@FeignClient(name = "GATEWAY-ZUUL",fallback = UsersClientCallback.class)
@FeignClient(name = "USERS-PROVIDER",fallback = UsersClientCallback.class)
public interface UsersClient {

    @RequestMapping("/gateway/provider/login")
    String login(@RequestHeader(name = "token") String token,
                 @RequestParam(name = "name") String name,
                 @RequestParam(name = "pwd") String pwd);

    @RequestMapping("/gateway/provider/load")
    String load();

    @RequestMapping("/select")
    Users select(Users users);

    @RequestMapping("/selMap")
    Map<String,String> selMap(@RequestParam Map<String,String> map);



}