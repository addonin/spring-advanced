package com.epam.springadvanced.service.impl;

import com.epam.springadvanced.entity.Role;
import com.epam.springadvanced.entity.User;
import com.epam.springadvanced.service.UserService;
import com.epam.springadvanced.service.exception.UserNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dimon
 * @since 26/03/16.
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UserNotRegisteredException();
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                /*user.getEnabled() == 1, TODO: figure out why it gets default value 0 instead explicitly set 1*/
                true, true, true, true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

}
