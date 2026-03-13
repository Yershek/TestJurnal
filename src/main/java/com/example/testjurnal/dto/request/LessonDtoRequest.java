package com.example.testjurnal.dto.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDtoRequest {
    private String title;
    private String teacherName;
    private String classroom;
    private Integer lessonNumber;
    
    // Принимаем время как строку из Flutter
    @JsonProperty("time")
    private String timeString;
    
    // Для совместимости с JSON
    private LocalTime startTime;
    private LocalTime endTime;
    private Long groupId;
    
    // Геттер для времени, преобразуем строку в LocalTime
    public LocalTime getStartTime() {
        if (startTime != null) return startTime;
        if (timeString != null && !timeString.isEmpty()) {
            try {
                // Поддерживаем формат "8:00-8:45"
                if (timeString.contains("-")) {
                    String start = timeString.split("-")[0].trim();
                    return LocalTime.parse(start, DateTimeFormatter.ofPattern("H:mm"));
                }
                // Поддерживаем формат "8:00"
                return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("H:mm"));
            } catch (Exception e) {
                // Если не удалось распарсить, возвращаем время по умолчанию
                return LocalTime.of(8, 0);
            }
        }
        return LocalTime.of(8, 0); // по умолчанию
    }
    
    public LocalTime getEndTime() {
        if (endTime != null) return endTime;
        if (timeString != null && !timeString.isEmpty()) {
            try {
                // Поддерживаем формат "8:00-8:45"
                if (timeString.contains("-")) {
                    String end = timeString.split("-")[1].trim();
                    return LocalTime.parse(end, DateTimeFormatter.ofPattern("H:mm"));
                }
            } catch (Exception e) {
                // Игнорируем ошибку, используем время начала + 45 минут
            }
        }
        // Если время начала установлено, добавляем 45 минут
        return getStartTime().plusMinutes(45);
    }
}
