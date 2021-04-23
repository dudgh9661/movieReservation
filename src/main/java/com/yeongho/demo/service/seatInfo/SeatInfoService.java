package com.yeongho.demo.service.seatInfo;

import com.yeongho.demo.domain.seatInfo.SeatInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SeatInfoService {

    private final SeatInfoRepository seatInfoRepository;

    @Transactional( readOnly = true ) //readOnly 옵션은 R 이외의 트랜잭션을 날리면 얘외발생
    public Long countReservedSeat(int theaterId, String time) {
        Long result = seatInfoRepository.countReservedSeat(theaterId, time);
        return result;
    }
}
