package com.eazybytes.eazyschool.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    /* the default behaviour provides a login page and hhtp basic(for api testing through postman and other softwares)
        the default security secures all the resources of our web application shown in below code by .anyRequest() method
     */

    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }
     */

    /*
    for production environment we bypass the default security using permitAll() method
     */

    /*

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }
     */

    /*
    as per requirement resources may require to be not accessible sometimes
    we use denyAll() method for such cases
    authentication login page is provided by when user tries to login a access denied message is displayed
     */

    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).denyAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

     */


    /*
    Custom security configurations
    based on the request
    remove .anyRequest().denyAll() and then use requestMatcher() for securing individual pages
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/login").permitAll()
                        /*
                         since the html file are made public by permitAll() method
                           the files(css,js,image,ets) under static package won't be accessible hence we need to make static.assets package
                           accessible too
                         */
                        .requestMatchers("/assets/**").permitAll()
                        .anyRequest().authenticated()

                /*
                        //.anyRequest().authenticated()
                        without this line the default url localhost:8080 will  display a login form but entering login credentials
                        will generate a resource denied message
                        we'll have to provide absolute path to access web resource
                        localhost:8080/home

                        if we add the line and use default url a login page will be displayed regardless of permitAll() in home.html file
                        then we'll have to use absolute path to access without having to authenticate

                        if anyRequest().permitAll() is used login page will not be displayed
                        but security custom configurations will be followed
                 */

        );
        http.formLogin(loginConfigurer -> loginConfigurer
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", false)
                .failureUrl("/login?error=true")

        );

        http.logout(logoutConfigurer -> logoutConfigurer
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)

        );

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    /* using InMemoryUserDetailsManager we can create multiple user who can access our website but
    this approach is only recommended for production because we will use data to store multiple
    users for live website and perform role base authentication based on the roles of users save in database
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
