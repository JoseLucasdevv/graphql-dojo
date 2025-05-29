package com.lucas.back.end.java.graphql.api.repository;

import com.lucas.back.end.java.graphql.api.controller.MovieResponse;
import com.lucas.back.end.java.graphql.api.entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<MovieResponse> getAllMovies();

}
