package com.cssl.client;

import com.cssl.pojo.Users;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 服务提供的默认实现
 * 作用：服务提供者崩溃使用该类执行返回
 */
@Component
public class UsersClientCallback implements UsersClient {

    @Override
    public String load(){
        return "降级";
    }

    @Override
    public Map<String, String> getMap(Map<String, String> map) {
        return null;
    }

    @Override
    public Users findById(Users user) {
        return new Users(0,"服务不可用","服务降级");
    }

    @Override
    public String login(String token,String username, String password) {
        return null;
    }
}
