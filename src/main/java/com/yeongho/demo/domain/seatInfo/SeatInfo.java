package com.yeongho.demo.domain.seatInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="seat_info")
@Getter
@NoArgsConstructor
@Entity
@IdClass(SeatInfo.class)
public class SeatInfo implements Serializable {
    @Column(name="theater_id")
    private int theaterId;

    @Id
    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="reservation")
    private boolean reservation;

    @Id
    @Column(name="time")
    private String time;
}
