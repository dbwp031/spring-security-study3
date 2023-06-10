package com.example.springsecuritystudy3.config;

import com.example.springsecuritystudy3.clients.ClientGithub;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@PropertySource("classpath:application-oauth.properties")
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    //    @Bean
//    public SecurityFilterChain securityFilterChainFormLogin(HttpSecurity http) throws Exception {
//        http.formLogin(Customizer.withDefaults());
//
//        http.csrf(c -> c.disable());
//
//        http.authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated());
//        return http.build();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChainBasicLogin(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//
//        http.csrf(c -> c.disable());
//
//        http.authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated());
//        return http.build();
//    }
    private final ClientGithub clientGithub;

    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientGithub.getGithubClientRegistration());
    }

    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth -> oauth
                .clientRegistrationRepository(clientRegistrationRepository())
                .failureUrl("/fail"));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated());


        return http.build();
    }
}
