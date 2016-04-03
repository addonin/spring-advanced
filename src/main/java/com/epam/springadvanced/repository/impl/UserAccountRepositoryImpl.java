package com.epam.springadvanced.repository.impl;

import com.epam.springadvanced.domain.entity.UserAccount;
import com.epam.springadvanced.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserAccount getUserAccount(long userId) {
        return null;
    }

    @Override
    public void fillAccount(long userId, float amount) {
        //add amount to existing value
    }

}
