package com.example.thiaco.security;

import com.example.thiaco.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
        return new AppAuthenticationSuccessHandler();
    }

    @Autowired
    public CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(crsf -> crsf.disable())
                .authorizeRequests(authorize -> {
                    authorize.requestMatchers("/resources/**","/assets/**","/static/**").permitAll()
                            .requestMatchers("/admin","/register/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
//                            .anyRequest().permitAll()
                    ;
                })
                .formLogin(login -> {
                    login.loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/index")
                            .permitAll()
                    ;
                })
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .cors(cors->cors.disable())
        ;
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(crsf -> crsf.disable())
//                .authorizeRequests(authorize -> {
//                    authorize.requestMatchers("/resources/**","/assets/**","/static/**").permitAll()
//                            .requestMatchers("/admin","/register/**").hasRole("ADMIN")
//                            .anyRequest().authenticated()
//                    //.anyRequest().permitAll()
//                    ;
//                })
//                .formLogin(login -> {
//                    login.loginPage("http://localhost:3000/")
//                            .loginProcessingUrl("/login")
//                            .defaultSuccessUrl("http://localhost:3000/nhansu")
//                            .permitAll().successHandler(appAuthenticationSuccessHandler());
//                })
//
//                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
//        ;
//        return http.build();
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
