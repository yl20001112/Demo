package com.example.service;

import com.cssl.pojo.Users;

import java.util.Map;

public interface UsersService {

    int login(String token,String name,String pwd);

    Users select(Users users);

    Map<String,String> selMap(Map<String,String> map);

    String load();

}
