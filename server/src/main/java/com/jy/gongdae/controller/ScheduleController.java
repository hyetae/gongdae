package com.jy.gongdae.controller;

import com.jy.gongdae.domain.Schedule;
import com.jy.gongdae.dto.ScheduleCreateDto;
import com.jy.gongdae.dto.ScheduleReadDto;
import com.jy.gongdae.dto.ScheduleUpdateDto;
import com.jy.gongdae.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule")
    public Schedule create(@ModelAttribute ScheduleCreateDto scheduleCreateDto) throws IOException {
        return scheduleService.createSchedule(scheduleCreateDto);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> findScheduleById(@PathVariable Long id){
        ScheduleReadDto scheduleReadDto = scheduleService.findScheduleById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("fix", scheduleReadDto.getFix());
        response.put("startDate", scheduleReadDto.getStartDate());
        response.put("endDate", scheduleReadDto.getEndDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute
    ScheduleUpdateDto scheduleUpdateDto) throws IOException {
        Long scheduleId = scheduleService.updateSchedule(id, scheduleUpdateDto);
        return new ResponseEntity<>(scheduleId, HttpStatus.OK);
    }

    @DeleteMapping("/schedule/{id}")
    public Long delete(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return id;
    }
}
