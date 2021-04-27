package com.yeongho.demo.web.Dto;

import com.yeongho.demo.domain.customers.Customers;
import com.yeongho.demo.domain.movies.Movies;
import com.yeongho.demo.domain.movies.MoviesRepository;
import com.yeongho.demo.service.movies.MoviesService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public class ReservationCompleteDto {
    private int theaterId;
    private String name;
    private String time;
    private int customerNumber;
    private String seatNumber;
    private int price;
    private String phoneNumber;

    @Builder
    public ReservationCompleteDto(int theaterId, String name, String time, int customerNumber,
                           String seatNumber, int price, String phoneNumber) {
        this.theaterId = theaterId;
        this.name = name;
        this.time = time;
        this.customerNumber = customerNumber;
        this.seatNumber = seatNumber;
        this.price = price;
        this.phoneNumber = phoneNumber;
    }
}
