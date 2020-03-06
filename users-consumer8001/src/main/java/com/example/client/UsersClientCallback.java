package com.example.client;

import com.cssl.pojo.Users;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 服务提供的默认实现;
 * 作用：服务提供者崩溃使用该类执行返回;
 */
@Component
public class UsersClientCallback implements UsersClient {

    @Override
    public String login(String token,String name, String pwd) {
        return "123";
    }

    @Override
    public Users select(Users users) {
        return new Users(0,"服务不可用","服务降级");
    }

    @Override
    public Map<String, String> selMap(Map<String, String> map) {
        return null;
    }

    @Override
    public String load() {
        return "降级";
    }

}
