package com.security.springsecurity.adapter.rest;

import com.security.springsecurity.application.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final CustomerService customerService;

    public TokenController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/token")
    public String getToken(@RequestParam("userName") String userName, @RequestParam("password") String password){
        String token = customerService.login(userName, password);
        if (StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}
