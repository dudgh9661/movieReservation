package com.yeongho.demo.web;

import com.yeongho.demo.domain.customers.Customers;
import com.yeongho.demo.domain.customers.CustomersRepository;
import com.yeongho.demo.domain.movies.MoviesRepository;
import com.yeongho.demo.domain.seatInfo.SeatInfo;
import com.yeongho.demo.domain.seatInfo.SeatInfoRepository;
import com.yeongho.demo.service.reservation.ReservationService;
import com.yeongho.demo.web.Dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final MoviesRepository moviesRepository;
    private final ReservationService reservationService;
    private final SeatInfoRepository seatInfoRepository;
    private final CustomersRepository customersRepository;
    private Object obj;
    //1. 영화이름을 가져온다.
    //2. 관람인원을 가져온다.
    //3. 총 가격을 계산한다.

    @PostMapping ("/reservation-confirm")
    public String reservaitonConfirm(@ModelAttribute ReservationConfirmDto reservationConfirmDto, Model model) {
        int totalPrice = moviesRepository.findPriceByName(reservationConfirmDto.getName()) * reservationConfirmDto.getCustomerNumber();
        reservationConfirmDto.setPrice(totalPrice);
        System.out.println("hhh"+reservationConfirmDto.getTheaterId());
        System.out.println("영화이름!! "+reservationConfirmDto.getName());
        System.out.println("hhh"+reservationConfirmDto.getTime());
        model.addAttribute("confirm", reservationConfirmDto);
        return "reservation-confirm";
    }

    @PostMapping("/reservation-complete")
//    @ResponseBody
    public String save(@ModelAttribute ReservationCompleteDto reservationCompleteDto, String name) {
        int movieId = moviesRepository.findMovieIdByName(name);
        String seatNumberTrim = reservationCompleteDto.getSeatNumber().trim();
        System.out.println("넘어온 좌석 정보 : " + seatNumberTrim);
        String[] seatNumber = seatNumberTrim.split(" ");

        for(int i = 0; i < seatNumber.length; i++) {
            String seatnumber = seatNumber[i];
            System.out.println("db에 들어가는 seatnum : " + seatnumber );
            CustomerDto customerDto = new CustomerDto(reservationCompleteDto.getPhoneNumber(),
                    movieId, seatnumber, reservationCompleteDto.getTheaterId());
            System.out.println(customerDto.toString());
            int customerId = reservationService.save(customerDto);

            SeatInfoResponseDto seatInfoResponseDto = new SeatInfoResponseDto(reservationCompleteDto.getTheaterId(),
                    seatnumber, reservationCompleteDto.getTime(), customerId);
            seatInfoRepository.save(seatInfoResponseDto.toEntity());
        }
        return "redirect:/";
    }

    @GetMapping("/reservation-check")
    public String reservationCheck() {
        return "reservation-check";
    }
    @GetMapping("/reservation-check-02")
    public String reservationCheck02(@RequestParam String phoneNumber, Model model) {
        //핸드폰 번호를 입력받는다
        System.out.println("phone : " + phoneNumber);
        //고객 테이블에 해당 핸드폰 번호와 매칭되는 모든 로우들을 가져온다.
        //phoneNumber, movieId, seatNumber, theaterId
        List<Customers> customerList = customersRepository.findByPhoneNumber(phoneNumber);

        //1.핸드폰 번호를 이용해 쿼리문을 날린다.
        //SELECT c.customer_id, c.theater_id, m.name, s.time, c.seat_number
        // FROM customer c, movie m, seat_info s
        // WHERE c.theater_id = s.theater_id AND c.movie_id = m.movie_Id and c.customer_id = s.customer_id and c.phone_number="01096615045";

        //2. 상영관, 영화이름, 상영시간, 좌석을 가져온다.
        //3. 또 다른 Dto를 만들어, 이 데이터들 + 관람인원, 총가격으로 초기화
        //4. 이 데이터를 프론트로 넘긴다.
        List<CustomerConfirmDto> customerConfirmDto
                = customersRepository.getCustomerConfirm(phoneNumber);

        List<TicketCheckDto> ticketCheckList = new ArrayList<>();
        //List<CustomerConfirmDto> 집합을 만들어서 대입
        List<ConfirmListDto> confirmListDto = new ArrayList<>();
        for(CustomerConfirmDto i : customerConfirmDto) {
            int theaterId = i.getTheaterId();
            String name = i.getName();
            String time = i.getTime();
            int customerId = i.getCustomerId();

            List<CustomerConfirmDto> tmp = new ArrayList<>();

            for( CustomerConfirmDto j : customerConfirmDto) {
                if(theaterId == j.getTheaterId() && name.equals(j.getName())
                        && time.equals(j.getTime())) {
                    if( !isOverlap(confirmListDto, customerId) ) {
                        tmp.add(j);
                    }
                }
            }
            //list, totalprice, customernumber
            if( tmp.size() == 0 ) continue;
            ConfirmListDto confirmTmp = new ConfirmListDto(tmp, tmp.size() * i.getPrice(), tmp.size());
            confirmListDto.add(confirmTmp);
        }

        for(int i = 0; i < confirmListDto.size(); i++) {
            String seat = "";
            for(int j = 0; j < confirmListDto.get(i).customerConfirmDto.size(); j++) {
                seat = seat.concat(confirmListDto.get(i).customerConfirmDto.get(j).getSeatNumber()+" ");
            }
            seat = seat.substring(0, seat.length()-1); //마지막 공백 삭제
            TicketCheckDto ticketCheckDto = new TicketCheckDto(confirmListDto.get(i).customerConfirmDto.get(0).getTheaterId(),
                    confirmListDto.get(i).customerConfirmDto.get(0).getName(),
                    confirmListDto.get(i).customerConfirmDto.get(0).getTime(),
                    confirmListDto.get(i).getCustomerNumber(),
                    seat, confirmListDto.get(i).getTotalPrice());
            ticketCheckList.add(ticketCheckDto);
        }

        for(int i = 0; i < ticketCheckList.size(); i++) {
            System.out.println(ticketCheckList.get(i).toString());
        }

        model.addAttribute("ticketCheckList", ticketCheckList);
        model.addAttribute("phoneNumber", phoneNumber);

        return "reservation-check-page";
    }

    public static boolean isOverlap(List<ConfirmListDto> confirmList, int customerId) {
        for(int i = 0; i < confirmList.size(); i++) {
            List<CustomerConfirmDto> list = confirmList.get(i).getCustomerConfirmDto();
            for(int j = 0; j < list.size(); j++) {
                if( customerId == list.get(j).getCustomerId()) return true;
            }
        }
        return false;
    }
}
