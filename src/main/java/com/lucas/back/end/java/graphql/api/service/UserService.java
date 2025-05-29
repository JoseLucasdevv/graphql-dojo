package com.lucas.back.end.java.graphql.api.service;

import com.lucas.back.end.java.graphql.api.controller.MovieResponse;
import com.lucas.back.end.java.graphql.api.controller.UserInput;
import com.lucas.back.end.java.graphql.api.entity.Movie;
import com.lucas.back.end.java.graphql.api.entity.User;
import com.lucas.back.end.java.graphql.api.repository.MovieRepository;
import com.lucas.back.end.java.graphql.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    UserService(UserRepository userRepository, MovieRepository movieRepository){
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }


    public List<User> getUsers(){

        return this.userRepository.findAll();
    }

    public User getUserById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

    public User addUser(UserInput userInput){
        User user = new User();
        user.setName(userInput.name());
        user.setEmail(userInput.email());
        user.setAge(userInput.age());

        return this.userRepository.save(user);
    }

    public User updateUser(Long id , UserInput userInput){
        User userFromDb = this.userRepository.findById(id).orElseThrow();
        userFromDb.setName(userInput.name());
        userFromDb.setEmail(userInput.email());
        userFromDb.setAge(userInput.age());

        return this.userRepository.save(userFromDb);
    }

    public Boolean deleteUser(Long id){
        Boolean flag = false;
        User user = this.userRepository.findById(id).orElse(null);
        if(user != null){
            this.userRepository.delete(user);
            flag = true;
        }

        return flag;
    }


    public List<Movie> getMovieByName(Long userId, String name){
        User user = this.userRepository.findById(userId).orElseThrow();
        return user.getMovies().stream().filter(movie -> movie.getName().contains(name)).toList();
    }

    public List<MovieResponse> getAllMovie(){
        return this.movieRepository.getAllMovies();
    }


}
