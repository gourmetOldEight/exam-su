package com.exam.writtenexam.config;

import lombok.Getter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring Security test data
 */
public class SecurityConstant {
    /**
     * key：NAME，value：password
     */
    public static final Map<String, String> USER_MSG = new ConcurrentHashMap<>();

    public static final Map<String, String> JWT_MSG = new ConcurrentHashMap<>();

    static {
        // USER MSG
        USER_MSG.put("test", "$2a$10$KOvypkjLRv/iJo/hU5GOSeFsoZzPYnh2B4r7LPI2x8yBTBZhPLkhy");
        JWT_MSG.put("secretKey","my_secret");
        JWT_MSG.put("issuer","hello");
        JWT_MSG.put("header","Authorization");
        JWT_MSG.put("tokenHead","Bearer");
    }




}
