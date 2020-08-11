package com.example.film.springSecurity;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
             //   .loginPage("/login")//  定义当需要用户登录时候，转到的登录页面。
             //   .successForwardUrl("/checkUAP")
                .defaultSuccessUrl("/checkUAP");
            //    .usernameParameter("username")
            //    .passwordParameter("password")

        http.authorizeRequests()
                .antMatchers("/login","/checkUAP").hasRole("admin")// 定义哪些URL需要被保护、哪些不需要被保护
                //.antMatchers("*.html").permitAll()//使得*。html网页，不用登陆就可以访问到
                //.antMatchers("/checkUAP").hasRole("admin")
                .anyRequest().authenticated();// 任何请求,登录后可以访问
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("1").roles("user")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
        auth.inMemoryAuthentication().withUser("admin").password("1").roles("admin")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
    }

}
