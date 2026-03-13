package com.example.testjurnal.repository;

import com.example.testjurnal.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findSchedulesByScheduleDateAndGroupId(LocalDate scheduleDate, Long groupId);
    Optional<Schedule> findFirstByScheduleDateAndGroupId(LocalDate scheduleDate, Long groupId);
}
