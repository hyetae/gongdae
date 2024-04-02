package com.jy.gongdae.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ScheduleUpdateDto {
    private int fix;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public ScheduleUpdateDto(int fix, LocalDateTime startDate, LocalDateTime endDate){
        this.fix = fix;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
