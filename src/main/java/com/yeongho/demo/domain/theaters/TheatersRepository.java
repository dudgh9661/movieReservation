package com.yeongho.demo.domain.theaters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheatersRepository extends JpaRepository<Theaters, Integer> {
    @Query("SELECT t FROM Theaters t")
    List<Theaters> findAllTheaters();
}
