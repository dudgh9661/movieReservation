package com.yeongho.demo.domain.schedules;

import com.yeongho.demo.domain.movies.Movies;
import com.yeongho.demo.domain.theaters.Theaters;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "schedule")
@Getter
@NoArgsConstructor
@Entity
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private int scheduleId;

    @Column(name="time")
    private String time;

    @Column(name="movie_id")
    private int movieId;

    @Column(name="theater_id")
    private int theaterId;

    @ManyToOne
    @JoinColumn(name="movie_id", updatable = false, insertable = false)
    private Movies movies;

    @ManyToOne
    @JoinColumn(name="theater_id", updatable = false, insertable = false)
    private Theaters theaters;

    @Builder
    public Schedules(int scheduleId, String time, int movieId, int theaterId) {
        this.scheduleId = scheduleId;
        this.time = time;
        this.movieId = movieId;
        this.theaterId = theaterId;
    }

}
