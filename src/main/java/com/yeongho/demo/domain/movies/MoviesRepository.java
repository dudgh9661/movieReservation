package com.yeongho.demo.domain.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    //@Query(value = "select * from Account where name = ?1", nativeQuery = true)
    //List<Account> findByusernamesQueryNative(String name);
    @Query("SELECT m FROM Movies m ORDER BY m.movieId ASC")
    List<Movies> findAllAsc();
}
