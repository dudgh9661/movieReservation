package com.yeongho.demo.service.schedules;

import com.yeongho.demo.domain.schedules.SchedulesRepository;
import com.yeongho.demo.web.Dto.MoviesListResponseDto;
import com.yeongho.demo.web.Dto.SchedulesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SchedulesService {
    private final SchedulesRepository schedulesRepository;

    @Transactional( readOnly = true ) //readOnly 옵션은 R 이외의 트랜잭션을 날리면 얘외발생
    public List<SchedulesListResponseDto> findAllInfo() { //모든 영화목록을 알려준다.
        return schedulesRepository.findAllInfo().stream()
                .map(SchedulesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
