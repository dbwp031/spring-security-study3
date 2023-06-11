package com.example.springsecuritystudy3.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@PropertySource("classpath:application-oauth.properties")
@RestController
public class HelloRestController {
    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String githubClientId;
    String encodedUrl = UriUtils.encode(url, StandardCharsets.UTF_8);

    @GetMapping("/hellorest")
    public Map<String, String> helloRest() {
        String url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("https://github.com/login")
                .queryParam("client_id", githubClientId)
                .queryParam("return_to", "")
                .queryParam("redirect_uri", UriUtils.encode("http://localhost:8080/login/oauth2/code/github", StandardCharsets.UTF_8))
                .queryParam("response_type", "code")
                .queryParam("scope", "email", "profile")
                .toUriString();
        Map<String, String> output = new HashMap<>();
        output.put("url", url);
        return
    }
}
