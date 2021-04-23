package com.yeongho.demo.web.Dto;
import lombok.Getter;


@Getter
public class SeatInfoResponseDto {
    private int theaterId;
    private int reservedSeatNumber;

    public SeatInfoResponseDto(int theaterId, int reservedSeatNumber) {
        this.theaterId = theaterId;
        this.reservedSeatNumber = reservedSeatNumber;
    }
}
