package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Schedule;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ScheduleCreateDto {
    private int fix;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Schedule toEntity(){
        return Schedule.builder()
                .fix(fix)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
