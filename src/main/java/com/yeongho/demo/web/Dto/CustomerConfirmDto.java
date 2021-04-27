package com.yeongho.demo.web.Dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomerConfirmDto {
    private int customerId;
    private int theaterId;
    private String name;
    private String time;
    private String seatNumber;
    private int price;
    private int totalPrice;
    private int customerNumber;

    public CustomerConfirmDto(int customerId,int theaterId, String name, String time, String seatNumber, int price) {
        this.customerId = customerId;
        this.theaterId = theaterId;
        this.name = name;
        this.time = time;
        this.seatNumber = seatNumber;
        this.price = price;
    }
}
