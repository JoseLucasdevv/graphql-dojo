package com.lucas.back.end.java.graphql.api.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContentPageable<T> {
    private List<T> content;
    private int actualPage;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private int size;
    private Long offSet;
    private int totalPage;
}
