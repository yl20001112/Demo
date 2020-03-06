package com.cssl.client;

import com.cssl.filter.FeignHeaderConfiguration;
import com.cssl.pojo.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@FeignClient(name="USERS-PROVIDER")
//@FeignClient(name="USERS-PROVIDER",fallback = UsersClientCallback.class)
//@FeignClient(name = "GATEWAY-ZUUL", configuration = FeignHeaderConfiguration.class)
public interface UsersClient {

    @PostMapping("/gateway/provider/load")
    public String load();

    //@PostMapping("/gateway/provider/login")
    @PostMapping("/login")
    public String login(@RequestHeader(name="token") String token,
                        @RequestParam String username,
                        @RequestParam String password);

    @PostMapping("/findMap")
    public Map<String,String> getMap(@RequestParam Map<String,String> map);

    //@PostMapping("/gateway/provider/findById")
    @PostMapping("/findById")
    public Users findById(Users user);



}
