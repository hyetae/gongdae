package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ScheduleReadDto {
    private int fix;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public ScheduleReadDto(Schedule schedule){
        this.fix = schedule.getFix();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
    }

}
