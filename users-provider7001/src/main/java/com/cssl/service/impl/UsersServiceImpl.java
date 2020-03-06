package com.cssl.service.impl;

import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Override
    public Users queryById(Users user){
        if(user.getId()==0){
            return null;
        }
        return user;
    }

    @Override
    public boolean queryByUsername(String username, String password) {
        if(username.equals("admin"))
            return true;
        return false;
    }
}
