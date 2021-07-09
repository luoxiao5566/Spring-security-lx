package com.security.springsecurity.adapter.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPo {
    @Id
    private Long id;
    private String userName;
    private String password;
    private String token;
}
