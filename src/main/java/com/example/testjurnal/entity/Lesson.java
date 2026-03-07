package com.example.testjurnal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "classroom")
    private String classroom;

    @Column(name = "lesson_number")
    private Integer lessonNumber;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
