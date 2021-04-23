package com.yeongho.demo.domain.schedules;

import com.yeongho.demo.domain.theaters.Theaters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {

    @Query("SELECT s FROM Schedules s, Movies m, Theaters t WHERE t.theaterId = s.theaterId AND m.movieId = s.movieId")
    List<Schedules> findAllInfo();
}
