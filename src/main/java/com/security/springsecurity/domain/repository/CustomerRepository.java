package com.security.springsecurity.domain.repository;

import com.security.springsecurity.adapter.persistence.jpa.CustomerPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerPo, Long> {
    @Query(value = "SELECT u FROM CustomerPo u WHERE u.userName = ?1 AND u.password = ?2")
    Optional<CustomerPo> login(String userName, String password);

    Optional<CustomerPo> findByToken(String token);

    @Override
    Optional<CustomerPo> findById(Long aLong);
}
