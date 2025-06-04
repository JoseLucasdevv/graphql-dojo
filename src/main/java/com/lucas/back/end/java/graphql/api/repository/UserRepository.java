package com.lucas.back.end.java.graphql.api.repository;

import com.lucas.back.end.java.graphql.api.entity.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {



}
