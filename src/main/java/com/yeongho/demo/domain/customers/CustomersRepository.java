package com.yeongho.demo.domain.customers;

import com.yeongho.demo.web.Dto.CustomerConfirmDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
    @Query("SELECT c FROM Customers c WHERE c.phoneNumber= :phoneNumber")
    List<Customers> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT new com.yeongho.demo.web.Dto.CustomerConfirmDto(c.customerId, c.theaterId, m.name, s.time, c.seatNumber, m.price) FROM Customers c, Movies  m, SeatInfo s " +
            "WHERE c.theaterId = s.theaterId AND c.movieId=m.movieId AND c.customerId=s.customerId " +
            "AND c.phoneNumber= :phoneNumber")
    List<CustomerConfirmDto> getCustomerConfirm(@Param("phoneNumber") String phoneNumber);
}
