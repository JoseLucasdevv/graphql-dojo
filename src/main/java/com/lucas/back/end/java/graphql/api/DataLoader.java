package com.lucas.back.end.java.graphql.api;

import com.lucas.back.end.java.graphql.api.entity.Movie;
import com.lucas.back.end.java.graphql.api.entity.User;
import com.lucas.back.end.java.graphql.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        User user = new User();
        user.setName("Lucas");
        user.setEmail("<EMAIL>");
        user.setMovies(Set.of(new Movie("The Godfather",1972),new Movie("The Godfather: Part II",1974),new Movie("Interstellar",1970)));
        user.setAge(25);
        User user2 = new User();
        user2.setName("Leo");
        user2.setEmail("<EMAIL>");
        user2.setAge(22);
        user2.setMovies(Set.of(new Movie("The Godfather",1972),new Movie("The Godfather: Part II",1974),new Movie("Interstellar",1970)));
        User user3 = new User();
        user3.setName("ravi");
        user3.setEmail("<EMAIL>");
        user3.setAge(32);
        user3.setMovies(Set.of(new Movie("The Godfather",1972),new Movie("The Godfather: Part II",1974),new Movie("Interstellar",1970)));

        this.userRepository.saveAll(Arrays.asList(user,user2,user3));


    }
}
