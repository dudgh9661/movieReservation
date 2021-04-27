package com.yeongho.demo.domain.customers;

import com.yeongho.demo.domain.movies.Movies;
import com.yeongho.demo.domain.seatInfo.SeatInfo;
import com.yeongho.demo.domain.theaters.Theaters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="customer")
@Getter
@Entity
public class Customers {
    @Id
    @Column(name="customer_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="movie_id")
    private int movieId;

    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="theater_id")
    private int theaterId;

    @ManyToOne
    @JoinColumn(name="movie_id", referencedColumnName = "movie_id", updatable = false, insertable = false)
    private Movies movies;

    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "customer_id", updatable = false, insertable = false)
//    @JoinColumn(name="theater_id", updatable = false, insertable = false)
    private SeatInfo SeatInfo;

    @Builder
    public Customers(String phoneNumber, int movieId, String seatNumber, int theaterId) {
        this.phoneNumber= phoneNumber;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
    }

    public Customers() {

    }
}
