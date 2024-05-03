package com.eazybytes.eazyschool.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/holidays").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        /*
                         since the html file are made public by permitAll() method
                           the files(css,js,image,ets) under static package won't be accessible hence we need to make static.assets package
                           accessible too
                         */
                        .requestMatchers("/assets/**").permitAll()
                        .anyRequest().permitAll()

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
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
