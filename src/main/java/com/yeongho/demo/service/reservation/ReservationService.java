package com.yeongho.demo.service.reservation;

import com.yeongho.demo.domain.seatInfo.SeatInfoRepository;
import com.yeongho.demo.web.Dto.MoviesListResponseDto;
import com.yeongho.demo.web.Dto.ReservedSeatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private SeatInfoRepository seatInfoRepository;

//    moviesRepository.findAllMovies().stream()
//                .map(MoviesListResponseDto::new)
//                .collect(Collectors.toList());

//    @Transactional(readOnly = true)
//    public List<ReservedSeatDto> findByReservedSeat(int theaterId, String time) {
//        System.out.println(" HHHHHHEre!!! " + theaterId + time);
//        return seatInfoRepository.findByReservedSeat(theaterId, time).stream()
//                .map(ReservedSeatDto::new)
//                .collect(Collectors.toList())
//    }
}
