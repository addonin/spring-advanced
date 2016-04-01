package com.epam.springadvanced.repository;

import com.epam.springadvanced.domain.entity.UserAccount;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
public interface UserAccountRepository {

    UserAccount getUserAccount(long userId);

    void fillAccount(long userId, float amount);

}
