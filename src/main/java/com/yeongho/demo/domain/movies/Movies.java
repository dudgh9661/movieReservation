package com.yeongho.demo.domain.movies;

import com.yeongho.demo.domain.schedules.Schedules;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "movie")
@Getter
@NoArgsConstructor
@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int movieId; //primary key

    @Column(name="name", length = 500, nullable = false )
    private String name;

    @Column(name="price", nullable= false )
    private int price;

    @Builder
    public Movies(int movieId, String name, int price) {
        this.movieId = movieId;
        this.name = name;
        this.price = price;
    }
}
