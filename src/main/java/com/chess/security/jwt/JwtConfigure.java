package com.chess.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtConfigure extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    final JwtTokenProvider jwtTokenProvider;

    final JwtTokenFilter jwtTokenFilter;

    public JwtConfigure(JwtTokenProvider jwtTokenProvider,
            JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}
