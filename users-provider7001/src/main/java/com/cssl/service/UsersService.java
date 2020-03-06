package com.cssl.service;

import com.cssl.pojo.Users;

public interface UsersService {

    public Users queryById(Users user);

    public boolean queryByUsername(String username,String password);

}
