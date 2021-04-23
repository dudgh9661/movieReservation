package com.yeongho.demo.web.Dto;

import com.yeongho.demo.domain.movies.Movies;
import com.yeongho.demo.domain.schedules.Schedules;
import com.yeongho.demo.domain.theaters.Theaters;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class SchedulesListResponseDto {

    private int theaterId;
    private String time;
    private String name;
    private int totalSeatNumber;
    private Movies movies;
    private Theaters theaters;

    public SchedulesListResponseDto(Schedules entity) {
        this.theaterId = entity.getTheaterId();
        this.time = entity.getTime();
        this.name = entity.getMovies().getName();
        this.totalSeatNumber = entity.getTheaters().getTotalSeatNumber();
    }
}
