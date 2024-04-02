package com.jy.gongdae.repository;

import com.jy.gongdae.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s ORDER BY s.createDate ASC")
    List<Schedule> findScheduleAllAsc();
}
