package com.example.testjurnal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "course_number")
    private Integer courseNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
