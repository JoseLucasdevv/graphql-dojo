package com.lucas.back.end.java.graphql.api.repository;

import com.lucas.back.end.java.graphql.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
