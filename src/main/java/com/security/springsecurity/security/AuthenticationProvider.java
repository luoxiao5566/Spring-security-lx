package com.security.springsecurity.security;

import com.security.springsecurity.application.service.CustomerService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final CustomerService customerService;

    public AuthenticationProvider(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        return Optional.ofNullable(usernamePasswordAuthenticationToken.getCredentials())
                .map(String::valueOf)
                .flatMap(customerService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
       /* try {
            Object o = Optional.ofNullable(token)
                    .map(String::valueOf)
                    .flatMap(customerService::findByToken)
                    .orElseThrow(() -> new UsernameNotFoundException("cannot find user with authentication token= " + token));
            userDetails = (UserDetails) o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
    }
}
