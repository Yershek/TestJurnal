package com.example.testjurnal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScheduleLesson> lessons;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

