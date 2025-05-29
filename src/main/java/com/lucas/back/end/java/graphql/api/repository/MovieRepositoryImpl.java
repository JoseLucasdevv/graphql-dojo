package com.lucas.back.end.java.graphql.api.repository;

import com.lucas.back.end.java.graphql.api.controller.MovieResponse;
import com.lucas.back.end.java.graphql.api.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public class MovieRepositoryImpl implements MovieRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MovieResponse> getAllMovies() {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from MOVIES;");
        //List<Movie> list = entityManager.createNativeQuery(sb.toString(),Movie.class).getResultList();
        List<MovieResponse> list = entityManager.createNativeQuery(sb.toString(),MovieResponse.class).getResultList();

        return list;
    }
}

