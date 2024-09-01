//package com.examvision.ExamVision.Config;
//
//import com.examvision.ExamVision.Service.UserServiceDetail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private UserServiceDetail userServiceDetail;
//
//    @Autowired
//    private JwtAuthFilter jwtAuthFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/api/register", "/api/login").permitAll()
//
//                        // Category permissions
//                        .requestMatchers(HttpMethod.POST, "/api/category/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/category/**").hasAnyAuthority("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority("ADMIN")
//
//                        // Quiz permissions
//                        .requestMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("ADMIN")
//
//                        // Question permissions
//                        .requestMatchers(HttpMethod.POST, "/api/question/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/question/**").hasAnyAuthority("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/question/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/question/**").hasAuthority("ADMIN")
//
//                        // QuizResult permissions
//                        .requestMatchers(HttpMethod.POST, "/api/quizResult/**").hasAuthority("USER")
//                        .requestMatchers(HttpMethod.GET, "/api/quizResult/all/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/quizResult/**").hasAnyAuthority("USER", "ADMIN")
//
//                        .anyRequest().denyAll()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userServiceDetail);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}



package com.examvision.ExamVision.Config;

import com.examvision.ExamVision.Service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserServiceDetail userServiceDetail;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request-> request.requestMatchers("/auth/**", "/public/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/adminuser/**").hasAnyAuthority("ADMIN", "USER")

//                        .requestMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/quizzes/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/quizzes/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/quizzes/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/quizzes/**").hasAuthority("ADMIN")

                        // Question permissions
                        .requestMatchers(HttpMethod.POST, "/api/questions/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/questions/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/questions/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/questions/**").hasAuthority("ADMIN")

                        // QuizResult permissions
                        .requestMatchers(HttpMethod.POST, "/api/quiz-results/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/quiz-results/all/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/quiz-results/**").hasAnyAuthority("USER", "ADMIN")
                        // Topic permissions
                        .requestMatchers(HttpMethod.POST, "/api/topics/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/topics/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/topics/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/topics/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userServiceDetail);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
