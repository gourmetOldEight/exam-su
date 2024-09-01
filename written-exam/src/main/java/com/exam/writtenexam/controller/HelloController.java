package com.exam.writtenexam.controller;

import com.exam.writtenexam.config.JwtUtils;
import com.exam.writtenexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/helloWorld")
    public String hello() {
        return "Hello World";
    }





}
