package com.yeongho.demo.web;

import com.yeongho.demo.domain.movies.Movies;
import com.yeongho.demo.service.movies.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MoviesApiController {

    private final MoviesService moviesService;

}
