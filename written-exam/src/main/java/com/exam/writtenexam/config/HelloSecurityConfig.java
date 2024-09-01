package com.exam.writtenexam.config;

import com.exam.writtenexam.entity.User;
import com.exam.writtenexam.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;



@EnableWebSecurity
@Configuration
public class HelloSecurityConfig   {

    @Value("${whiteApi}")
    private String whiteApi;

    @Resource
    private UserService userService;

    @Resource
    private JwtFilter jwtFilter;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // close csrf
                .csrf(AbstractHttpConfigurer::disable)
                // Configuring intercept information
                .authorizeHttpRequests(authorization -> authorization
                        // whiteApi
                        .requestMatchers(whiteApi).permitAll()
                        // role
                        .anyRequest().access((authentication, object) -> {
                            String requestURI = object.getRequest().getRequestURI();
                            PathMatcher pathMatcher = new AntPathMatcher();
                                if (pathMatcher.match(whiteApi, requestURI)) {
                                    return new AuthorizationDecision(true);
                                }
                            Authentication authentication1 = authentication.get();
                            if(!authentication1.getPrincipal().equals("anonymousUser")){
                                return new AuthorizationDecision(true);
                            }
                            return new AuthorizationDecision(false);
                        })
                )
                .userDetailsService(userService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
