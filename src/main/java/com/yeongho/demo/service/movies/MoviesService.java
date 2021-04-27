package com.yeongho.demo.service.movies;

import com.yeongho.demo.domain.movies.MoviesRepository;
import com.yeongho.demo.web.Dto.MoviesInfoResponseDto;
import com.yeongho.demo.web.Dto.MoviesListResponseDto;
import com.yeongho.demo.web.Dto.ReservationCompleteDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //final 변수의 생성자를 자동으로 생성
@Service
public class MoviesService {
    private final MoviesRepository moviesRepository;

    @Transactional( readOnly = true ) //readOnly 옵션은 R 이외의 트랜잭션을 날리면 얘외발생
    public List<MoviesListResponseDto> findAllMovies() { //모든 영화목록을 알려준다.
        return moviesRepository.findAllMovies().stream()
                .map(MoviesListResponseDto::new)
                .collect(Collectors.toList());
    }

    public MoviesInfoResponseDto findMovieInfo(int theaterId, String name, String time, int totalSeatNumber, int remainSeat) {
        return new MoviesInfoResponseDto(theaterId, name, time, totalSeatNumber, remainSeat);
    }

}
