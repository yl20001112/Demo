package com.example.service.impl;

import com.cssl.pojo.Users;
import com.example.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

    @Override
    public int login(String token,String name, String pwd) {
        if(name.equals("kdb"))
            return 1;
        return 0;

    }

    @Override
    public Users select(Users users) {
        if(users.getId()==0)
            return null;
        return users;
    }

    @Override
    public Map<String, String> selMap(Map<String, String> map) {
        return map;
    }

    @Override
    public String load() {
        return null;
    }
}
