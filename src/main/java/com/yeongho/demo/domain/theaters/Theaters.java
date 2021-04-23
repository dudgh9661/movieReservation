package com.yeongho.demo.domain.theaters;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "theater")
@Getter
@NoArgsConstructor
@Entity
public class Theaters {
    @Id
    @Column(name="theater_id")
    private int theaterId;

    @Column(name="total_seat_number")
    private int totalSeatNumber;

    @Builder
    public Theaters(int theaterId, int totalSeatNumber) {
        this.theaterId = theaterId;
        this.totalSeatNumber = totalSeatNumber;
    }
}
