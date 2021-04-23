package com.yeongho.demo.domain.seatInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface SeatInfoRepository extends JpaRepository<SeatInfo, SeatInfoId> {
    //상영관 번호, 상영시간이 같은 것끼리 묶는다. ex) 1관, 9시
    //그룹화된 애들을 count하면, 해당 상영관, 해당 시간에 예약된 좌석 수를 알 수 있다.
    //select theaterId, count(*)
    //from seat_info
    //group by theater_id, time
    @Query("SELECT COUNT(s) FROM SeatInfo s WHERE s.theaterId = :theaterId AND s.time = :time")
    Long countReservedSeat(@Param("theaterId") int theaterId,
                                 @Param("time") String time); //이 쿼리에 대한 결과값은?

    @Query("SELECT s.seatNumber FROM SeatInfo s WHERE s.theaterId = :theaterId AND s.time = :time AND s.reservation = true")
    List<Object> findByReservedSeat(@Param("theaterId") int theaterId,
                                   @Param("time") String time);
}
