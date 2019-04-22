package com.tutorial.repository;

import com.tutorial.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>,JpaSpecificationExecutor<Account> {
}
