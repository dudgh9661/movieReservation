package com.yeongho.demo.web;

import com.yeongho.demo.domain.seatInfo.SeatInfoRepository;
import com.yeongho.demo.service.movies.MoviesService;
import com.yeongho.demo.service.reservation.ReservationService;
import com.yeongho.demo.service.seatInfo.SeatInfoService;
import com.yeongho.demo.service.schedules.SchedulesService;
import com.yeongho.demo.web.Dto.MoviesInfoResponseDto;
import com.yeongho.demo.web.Dto.ReservedSeatDto;
import com.yeongho.demo.web.Dto.SchedulesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MoviesService moviesService;
    private final SchedulesService schedulesService;
    private final SeatInfoService seatInfoService;
    private final ReservationService reservationService;
    private Long cnt;

    private final SeatInfoRepository seatInfoRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<SchedulesListResponseDto> schedulesList = schedulesService.findAllInfo();
        List<MoviesInfoResponseDto> moviesInfoResponseDto = new ArrayList<>();

        for( SchedulesListResponseDto s : schedulesList ) {
            cnt = seatInfoService.countReservedSeat(s.getTheaterId(), s.getTime());
            moviesInfoResponseDto.add(moviesService.findMovieInfo(s.getTheaterId(), s.getName(), s.getTime()
                    ,s.getTotalSeatNumber(), s.getTotalSeatNumber()-cnt.intValue()));
//            moviesInfoResponseDto.add(MoviesInfoResponseDto.builder().
//                    theaterId(s.getTheaterId()).
//                    name(s.getName()).
//                    time(s.getTime()).
//                    totalSeatNumber(s.getTotalSeatNumber()).
//                    remainSeat(s.getTotalSeatNumber() - cnt.intValue()).build());
//            model.addAttribute("movies", s);
//            model.addAttribute("seat", cnt);
        }
        model.addAttribute("moviesInfo",  moviesInfoResponseDto);
//        model.addAttribute("movies", schedulesService.findAllInfo(), cnt);
//        model.addAttribute("seat", seatInfoService.countReservedSeat(1,"09:00:00"));
        return "index";
    }

    @GetMapping("/reservation")
//    @ResponseBody
    public String reservation(@RequestParam int theaterId, @RequestParam String name, @RequestParam String time, Model model) {
        //파라미터로 넘어온 정보 : 상영관, 영화이름, 상영시간
        System.out.println("tttest" + theaterId + name + time);
        //좌석번호, 관람 인원수를 선택한다. (예약할 수 없는 좌석들 표시)
        List<Object> result = seatInfoRepository.findByReservedSeat(theaterId,time);
        List<ReservedSeatDto> reservedNumber = new ArrayList<>();
        List<String> select = new ArrayList<>();
        for(int i = 1; i <= 50; i++) {
            select.add("<option value="+i+">"+i+"</option>");
        }
        //<option value="2">2</option>-->


        //이거 2개 화면 3에서 쓰면 될듯?
//        List<MoviesInfoResponseDto> movieInfo = new ArrayList<>();
//        movieInfo.add(new MoviesInfoResponseDto(theaterId,name,time);

        for(  Object obj : result ) {
            System.out.print(obj);
            reservedNumber.add(new ReservedSeatDto(obj.toString()));
        }
        for( ReservedSeatDto str : reservedNumber) {
            System.out.print("gggggg : " + str.getReservedSeat());
        }
        //예약된 좌석을 받아온다.
        model.addAttribute("reserved", reservedNumber);
        model.addAttribute("theaterId", theaterId);
        model.addAttribute("name", name);
        model.addAttribute("time", time);
        model.addAttribute("selectNumber", select);
        //data를 JSON으로 묶어 reservation-confirm에 던져준다.
        return "reservation";
//        return obj;
    }

}
