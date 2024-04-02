package com.jy.gongdae.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends  BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    private int fix;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "space_table")
    private Space space;

    @Builder
    public Schedule(int fix, LocalDateTime startDate, LocalDateTime endDate, Space space){
        this.fix = fix;
        this.startDate = startDate;
        this.endDate = endDate;
        this.space = space;
    }

    public void update(int fix, LocalDateTime startDate, LocalDateTime endDate){
        this.fix = fix;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
