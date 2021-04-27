package com.yeongho.demo.service.reservation;

import com.yeongho.demo.domain.customers.CustomersRepository;
import com.yeongho.demo.domain.seatInfo.SeatInfoRepository;
import com.yeongho.demo.web.Dto.CustomerDto;
import com.yeongho.demo.web.Dto.MoviesListResponseDto;
import com.yeongho.demo.web.Dto.ReservedSeatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final CustomersRepository customersRepository;

    @Transactional
    public int save(CustomerDto customerDto) {
        return customersRepository.save(customerDto.toEntity()).getCustomerId();
    }

}
