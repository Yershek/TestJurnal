package com.example.testjurnal.repository;

import com.example.testjurnal.entity.ScheduleLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleLessonRepository extends JpaRepository<ScheduleLesson, Long> {
}
