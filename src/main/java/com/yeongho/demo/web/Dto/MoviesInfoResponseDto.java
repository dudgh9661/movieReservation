package com.yeongho.demo.web.Dto;

import lombok.Getter;

@Getter
public class MoviesInfoResponseDto {
    private int theaterId;
    private String name;
    private String time;
    private int totalSeatNumber;
    private int remainSeat;

    public MoviesInfoResponseDto(int theaterId, String name, String time, int totalSeatNumber, int remainSeat) {
        this.theaterId = theaterId;
        this.name = name;
        this.time = time;
        this.totalSeatNumber = totalSeatNumber;
        this.remainSeat = remainSeat;
    }
}
