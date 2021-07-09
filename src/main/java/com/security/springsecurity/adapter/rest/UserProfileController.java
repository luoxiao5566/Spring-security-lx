package com.security.springsecurity.adapter.rest;

import com.security.springsecurity.application.service.CustomerService;
import com.security.springsecurity.domain.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    private final CustomerService customerService;

    public UserProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Customer getUserDetail(@PathVariable Long id){
        return customerService.findById(id);
    }
}
