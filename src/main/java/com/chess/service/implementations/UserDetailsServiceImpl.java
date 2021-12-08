package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        UserEntity user = userService.findUserByUsername(login);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with %s not found", login));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
