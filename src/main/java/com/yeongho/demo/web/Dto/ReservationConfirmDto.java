package com.yeongho.demo.web.Dto;

import lombok.*;

@Getter
@Setter
public class ReservationConfirmDto {
    private int theaterId;
    private String name;
    private String time;
    private int customerNumber;
    private String seatNumber;
    private int price;

    public ReservationConfirmDto(int theaterId, String name, String time, int customerNumber, String seatNumber) {
        this.theaterId = theaterId;
        this.name = name;
        this.time = time;
        this.customerNumber = customerNumber;
        this.seatNumber = seatNumber;
    }

}
