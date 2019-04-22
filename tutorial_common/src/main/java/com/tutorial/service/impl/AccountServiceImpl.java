package com.tutorial.service.impl;

import com.tutorial.repository.AccountRepository;
import com.tutorial.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountRepository accountRepository;
}
