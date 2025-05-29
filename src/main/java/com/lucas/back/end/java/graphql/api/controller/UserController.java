package com.lucas.back.end.java.graphql.api.controller;

import com.lucas.back.end.java.graphql.api.entity.Movie;
import com.lucas.back.end.java.graphql.api.service.UserService;

import com.lucas.back.end.java.graphql.api.entity.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }

    @QueryMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @QueryMapping()
    public User getUserById(@Argument Long id){
        return this.userService.getUserById(id);
    }

    @QueryMapping
    public List<MovieResponse> getAllMovie(){
        return this.userService.getAllMovie();
    }

    @QueryMapping
    public List<Movie> getMovieByName(@Argument Long userId, @Argument String name){
        return this.userService.getMovieByName(userId,name);
    }

    @MutationMapping()
    public User addUser(@Argument UserInput userInput){
        return this.userService.addUser(userInput);
    }
    @MutationMapping()
    public User updateUser(@Argument Long id ,@Argument UserInput userInput){
        return this.userService.updateUser(id,userInput);
    }
    @MutationMapping()
    public Boolean deleteUser(@Argument Long id){
        return this.userService.deleteUser(id);
    }



}
