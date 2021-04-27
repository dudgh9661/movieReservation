package com.yeongho.demo.web.Dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TicketCheckDto {
    private int theaterId;
    private String name;
    private String time;
    private int customerNumber;
    private String seat;
    private int totalPrice;

    public TicketCheckDto(int theaterId, String name, String time, int customerNumber,
                          String seat, int totalPrice) {
        this.theaterId = theaterId;
        this.name = name;
        this.customerNumber = customerNumber;
        this.seat = seat;
        this.totalPrice = totalPrice;
        this.time = time;
    }
}
