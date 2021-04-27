package com.yeongho.demo.web.Dto;

import com.yeongho.demo.domain.customers.Customers;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomerDto {
    private String phoneNumber;
    private int movieId;
    private String seatNumber;
    private int theaterId;

    @Builder
    public CustomerDto(String phoneNumber, int movieId, String seatNumber, int theaterId) {
        this.phoneNumber = phoneNumber;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
    }

    public Customers toEntity() {
        return Customers.builder()
                .phoneNumber(phoneNumber)
                .movieId(movieId)
                .seatNumber(seatNumber)
                .theaterId(theaterId)
                .build();
    }
}
