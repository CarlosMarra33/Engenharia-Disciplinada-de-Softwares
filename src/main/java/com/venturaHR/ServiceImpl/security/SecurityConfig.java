package com.venturaHR.ServiceImpl.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity httpsec) throws Exception{
        httpsec.csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/cadastro").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors();
        httpsec.addFilterBefore(new SecurityFilter() ,UsernamePasswordAuthenticationFilter.class);
    }
}
