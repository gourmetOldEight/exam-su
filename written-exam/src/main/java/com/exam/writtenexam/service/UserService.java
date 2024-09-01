package com.exam.writtenexam.service;

import com.exam.writtenexam.config.SecurityConstant;
import com.exam.writtenexam.entity.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get user msg
        String password = SecurityConstant.USER_MSG.get(username);
        if (StringUtils.isBlank(password)) {
            throw new UsernameNotFoundException("error name or password");
        }
        // GET USER
        return User.builder()
                .userName(username)
                .password(password)
                .build();

    }
}
