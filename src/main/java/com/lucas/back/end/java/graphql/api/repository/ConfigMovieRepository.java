package com.lucas.back.end.java.graphql.api.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMovieRepository {
    @Bean
    public MovieRepository movieRepository(){
        return new MovieRepositoryImpl();
    }

}
