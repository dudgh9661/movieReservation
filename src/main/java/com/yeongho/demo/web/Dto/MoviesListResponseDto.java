package com.yeongho.demo.web.Dto;

import com.yeongho.demo.domain.movies.Movies;
import lombok.Getter;

@Getter
public class MoviesListResponseDto {
    private int movieId;
    private String name;
    private int price;

    public MoviesListResponseDto(Movies entity) {
        this.movieId = entity.getMovieId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }
}
