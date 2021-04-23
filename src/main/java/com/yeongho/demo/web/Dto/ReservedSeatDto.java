package com.yeongho.demo.web.Dto;

import lombok.Getter;

@Getter
public class ReservedSeatDto {
    private String reservedSeat;

    public ReservedSeatDto(String reservedSeat) {
        this.reservedSeat = reservedSeat;
    }
}
