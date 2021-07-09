package com.security.springsecurity.application.service;

import com.security.springsecurity.adapter.persistence.jpa.CustomerPo;
import com.security.springsecurity.domain.model.Customer;
import com.security.springsecurity.domain.repository.CustomerRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String login(String username, String password) {
        Optional<CustomerPo> customerPo = customerRepository.login(username, password);
        return customerPo.map(c -> {
            String token = UUID.randomUUID().toString();
            c.setToken(token);
            customerRepository.save(c);
            return token;
        }).orElse("");
    }

    public Optional<User> findByToken(String token) {
        Optional<CustomerPo> customerPo = customerRepository.findByToken(token);
        return customerPo.map(c -> {
            User user = new User(c.getUserName(),c.getPassword(),true,true,true,true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }).orElse(Optional.empty());
    }

    public Customer findById(Long id) {
        Optional<CustomerPo> customerPo = customerRepository.findById(id);
        CustomerPo customerPo1 = customerPo.map(customerPo2 -> {
            return customerPo.get();
        }).orElseThrow();
        return new Customer(customerPo1.getUserName(),customerPo1.getPassword(),customerPo1.getToken());
    }
}
