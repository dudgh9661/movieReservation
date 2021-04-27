package com.yeongho.demo.domain.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    //@Query(value = "select * from Account where name = ?1", nativeQuery = true)
    //List<Account> findByusernamesQueryNative(String name);
    @Query("SELECT m FROM Movies m")
    List<Movies> findAllMovies();

    @Query("SELECT m.price FROM Movies m WHERE m.name= :name")
    int findPriceByName(@Param("name") String name); //이 쿼리에 대한 결과값은?

    @Query("SELECT m.movieId FROM Movies m WHERE m.name= :name")
    int findMovieIdByName(@Param("name") String name);

    @Query("SELECT m.name FROM Movies m WHERE m.movieId= :movieId")
    String findMovieNameByMovieId(@Param("movieId") int movieId);
}
