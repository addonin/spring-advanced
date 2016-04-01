package com.epam.springadvanced.service.impl;

import com.epam.springadvanced.domain.entity.UserAccount;
import com.epam.springadvanced.repository.UserAccountRepository;
import com.epam.springadvanced.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount getUserAccount(long userId) {
        return null;
    }

    @Override
    public void fillAccount(long userId, float amount) {
        userAccountRepository.fillAccount(userId, amount);
    }

}
