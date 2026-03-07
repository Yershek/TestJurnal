package com.example.testjurnal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student extends BaseEntity{

    @Column(nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
