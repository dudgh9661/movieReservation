package com.yeongho.demo.domain.seatInfo;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class SeatInfoId implements Serializable {

    private String seatNumber;

    private String time;

    @Builder
    public SeatInfoId(String seatNumber, String time) {
        this.seatNumber = seatNumber;
        this.time = time;
    }

}
