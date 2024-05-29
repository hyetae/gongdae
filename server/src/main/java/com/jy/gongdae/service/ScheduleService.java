package com.jy.gongdae.service;

import com.jy.gongdae.domain.Schedule;
import com.jy.gongdae.dto.ScheduleCreateDto;
import com.jy.gongdae.dto.ScheduleReadDto;
import com.jy.gongdae.dto.ScheduleUpdateDto;
import com.jy.gongdae.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    public Schedule createSchedule(ScheduleCreateDto scheduleCreateDto){
        return scheduleRepo.save(scheduleCreateDto.toEntity());
    }

    public ScheduleReadDto findScheduleById(Long id){
        Schedule entity = scheduleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id = " + id));

        return new ScheduleReadDto(entity);
    }

    public Long updateSchedule(Long id, ScheduleUpdateDto scheduleUpdateDto){
        Schedule entity = scheduleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id" + id));

        entity.update(scheduleUpdateDto.getFix(), scheduleUpdateDto.getStartDate(), scheduleUpdateDto.getEndDate());

        return id;
    }

    public Long deleteSchedule(Long id){
        scheduleRepo.deleteById(id);

        return id;
    }
}
