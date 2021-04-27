package com.yeongho.demo.web.Dto;
import com.yeongho.demo.domain.seatInfo.SeatInfo;
import lombok.Getter;


@Getter
public class SeatInfoResponseDto {
    private int theaterId;
    private String reservedSeatNumber;
    private String time;
    private int customerId;

    public SeatInfoResponseDto(int theaterId, String reservedSeatNumber, String time, int customerId) {
        this.theaterId = theaterId;
        this.reservedSeatNumber = reservedSeatNumber;
        this.time = time;
        this.customerId = customerId;
    }

    public SeatInfo toEntity() {
        return SeatInfo.builder()
                .theaterId(theaterId)
                .seatNumber(reservedSeatNumber)
                .time(time)
                .reservation(true)
                .customerId(customerId)
                .build();
    }
}
