package com.lucas.back.end.java.graphql.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Movie {
    private String name;
    private Integer releaseYear;
}
