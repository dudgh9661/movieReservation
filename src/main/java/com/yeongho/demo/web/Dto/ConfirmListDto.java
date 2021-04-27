package com.yeongho.demo.web.Dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ConfirmListDto {
    public List<CustomerConfirmDto> customerConfirmDto;
    private int totalPrice;
    private int customerNumber;

    public ConfirmListDto(List<CustomerConfirmDto> customerConfirmDto, int totalPrice, int customerNumber) {
        this.customerConfirmDto = customerConfirmDto;
        this.totalPrice = totalPrice;
        this.customerNumber = customerNumber;
    }

}
