package com.example.testjurnal.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDtoRequest {
    private Long groupId;
    private LocalDate scheduleDate;
    private List<ScheduleLessonDtoRequest> lessons;

    @Override
    public String toString() {
        return "ScheduleDtoRequest{" +
                "groupId=" + groupId +
                ", scheduleDate=" + scheduleDate +
                ", lessons=" + lessons +
                '}';
    }
}
