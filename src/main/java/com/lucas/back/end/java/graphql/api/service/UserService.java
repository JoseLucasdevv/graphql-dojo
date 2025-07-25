package com.lucas.back.end.java.graphql.api.service;

import com.lucas.back.end.java.graphql.api.controller.MovieResponse;
import com.lucas.back.end.java.graphql.api.controller.UserInput;
import com.lucas.back.end.java.graphql.api.entity.Movie;
import com.lucas.back.end.java.graphql.api.entity.User;
import com.lucas.back.end.java.graphql.api.page.ContentPageable;
import com.lucas.back.end.java.graphql.api.repository.MovieRepository;
import com.lucas.back.end.java.graphql.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ContentPageable getUsers(int page, int size){

        if(page < 1)  page = 1;

        if(size < 1) size = 5;

        page = page - 1;
        Pageable query = PageRequest.of(page,size);

        ContentPageable contentPageable = new ContentPageable<User>();
        Page<User> user = this.userRepository.findAll(query);
        System.out.println(user.getTotalElements());
        user.getPageable().getOffset();
        System.out.println(user.getPageable());
        System.out.println(user.getTotalPages());
        System.out.println(user.getSize());
        System.out.println(user.hasNext());
        System.out.println(user.hasPrevious());

        contentPageable.setContent(user.getContent());
        contentPageable.setSize(user.getSize());
        contentPageable.setHasPrevious(user.hasPrevious());
        contentPageable.setHasNext(user.hasNext());
        contentPageable.setOffSet(user.getPageable().getOffset());
        contentPageable.setTotalPage(user.getTotalPages());
        contentPageable.setActualPage(page + 1);

        return contentPageable;

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

    public List<MovieResponse> getAllMovies(){
        return this.movieRepository.getAllMovies();
    }

}
