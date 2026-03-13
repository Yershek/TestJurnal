package com.example.testjurnal.repository;

import com.example.testjurnal.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    
    @Query("SELECT l FROM Lesson l WHERE l.group.id = :groupId AND l.createdAt >= :startDate AND l.createdAt < :endDate")
    Optional<List<Lesson>> findLessonsByGroupIdAndDate(@Param("groupId") Long groupId, @Param("startDate") LocalDate date);
    
    @Query("SELECT l FROM Lesson l WHERE l.group.id = :groupId")
    Optional<List<Lesson>> findLessonsByGroupId(@Param("groupId") Long groupId);
}
