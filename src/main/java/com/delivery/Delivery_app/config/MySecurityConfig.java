package com.delivery.Delivery_app.config;

import com.delivery.Delivery_app.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService userdetail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        /*    implementing security for all the urls

        http
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin();

         */

        /*   implementing security for the ("/foods") url only*/
      http
                .authorizeRequests()
                .antMatchers("/login").fullyAuthenticated()
                .and()
                .httpBasic();

   /* http
                .authorizeRequests()
                .antMatchers("/foods")
                .hasRole("ADMIN")
                .and()
                .httpBasic();

    */

    /*   http
                .authorizeRequests()
//                .antMatchers("/user***").permitAll()   // this will url will be permited to all type of user or .antMatchers("/home","/public/***","register").permitAll()  acess all url with public in it
                .antMatchers("/foods").hasRole("ADMIN")  // role base permission
                .anyRequest()
                .authenticated()
                .and()
//                .formLogin();
                .httpBasic();
*/



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userdetail).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();//password not encript
        return new BCryptPasswordEncoder();
    }
}
