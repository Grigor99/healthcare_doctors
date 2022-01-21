package com.example.healthcare.configs.hessian;


import com.example.healthcare.util.encoder.CustomEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Order(value = Ordered.LOWEST_PRECEDENCE)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class BackToBackSecurityConfigs extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/interconnect/**").httpBasic().and()
                .csrf().disable().
                cors().disable().
                headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/interconnect/**").hasAuthority("ROLE_INTERCONNECT")
                .antMatchers("/interconnect/**").authenticated();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("interconnect_user").password("interconnect_password").authorities("ROLE_INTERCONNECT").and()
                .passwordEncoder(CustomEncoder.getInstance());
    }
}