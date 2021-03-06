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
import org.springframework.web.bind.annotation.PostMapping;
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
    public String reservation(@RequestParam int theaterId, @RequestParam("name") String name, @RequestParam String time, Model model) {
        //??????????????? ????????? ?????? : ?????????, ????????????, ????????????
        System.out.println("tttest" + theaterId + name + time);
        //????????????, ?????? ???????????? ????????????. (????????? ??? ?????? ????????? ??????)
        List<Object> result = seatInfoRepository.findByReservedSeat(theaterId,time);
        List<ReservedSeatDto> reservedNumber = new ArrayList<>();
        List<String> select = new ArrayList<>(); //????????? ??????

        List<String> remainSeat = new ArrayList<>(); //????????? ??? ?????? ??????
        List<String> remainSeatSelect = new ArrayList<>();

        String SeatStr="";

        //????????? ????????? ?????????
        for(  Object obj : result ) {
            System.out.print(obj);
            reservedNumber.add(new ReservedSeatDto(obj.toString()));
        }
        for( ReservedSeatDto str : reservedNumber) {
            System.out.print("????????? ?????? : " + str.getReservedSeat());
        }

        //????????? ????????? ?????????, ????????? ???????????? ???????????????.
        if( theaterId == 1 ) { //1?????????
            remainSeat = getRemainSeat(reservedNumber,12,13);
        } else if ( theaterId == 2) { //2?????????
            remainSeat = getRemainSeat(reservedNumber,10,10);
        } else if( theaterId == 3) { //3?????????
            remainSeat = getRemainSeat(reservedNumber,12,12);
        }

        for(int i = 1; i <= 50; i++) {
            if( i == 1 ) select.add("<option value="+i+" selected>"+i+"</option>");
            else select.add("<option value="+i+">"+i+"</option>");
        }

//        for(int i = 0; i < remainSeat.size(); i++) {
//            remainSeatSelect.add("<option value="+remainSeat.get(i)+">"+remainSeat.get(i)+"</option>");
//        }

        //????????? ????????? ????????????.

        for(int i = 0; i < remainSeat.size(); i++) {
            SeatStr += remainSeat.get(i);
        }

        model.addAttribute("reserved", reservedNumber);
        model.addAttribute("theaterId", theaterId);
        model.addAttribute("name", name);
        model.addAttribute("time", time);
        model.addAttribute("selectNumber", select);
        model.addAttribute("remainSeat", SeatStr);
//        model.addAttribute("remainSeat", remainSeatSelect);
        //data??? JSON?????? ?????? reservation-confirm??? ????????????.
        return "reservation";
//        return obj;
    }
    public static boolean isReservedSeat(String seat, List<ReservedSeatDto> reservedNumber) {
        for(int i = 0; i < reservedNumber.size(); i++) {
            if( seat.equals(reservedNumber.get(i).getReservedSeat()) ) return true;
        }
        return false;
    }
    public static List<String> getRemainSeat(List<ReservedSeatDto> reservedNumber, int row, int col) {
        List<String> remainSeat = new ArrayList<>();

        for(int i = 0; i < row; i++) { //A~H
            String tmp = "<tr class=''test'>";
            for(int j = 1; j <= col; j++) { //1~13
                String seat = (char)(i+65) + "-" + j; // ex) A-1;
//                if( isReservedSeat(seat, reservedNumber)) continue;
                tmp += "<td>"+seat+"</td>";
//                remainSeat.add(seat);
//                System.out.print(seat);
            }
            tmp += "</tr>";
            remainSeat.add(tmp);
        }

        return remainSeat;
    }

}

