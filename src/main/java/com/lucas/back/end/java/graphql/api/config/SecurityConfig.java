package com.lucas.back.end.java.graphql.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkUri;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/graphiql/**","/graphiql","/webjars/**","/static/**").permitAll()
                .requestMatchers("/graphql","/graphql/**").authenticated()
                .anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(customize -> customize.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(this.converter())
                                .jwkSetUri(this.jwkUri)));;

        return http.build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> converter(){
        return new JwtAuthenticationConverter();
    }

}
