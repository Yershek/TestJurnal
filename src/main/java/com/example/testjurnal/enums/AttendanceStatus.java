package com.example.testjurnal.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AttendanceStatus {
    PRESENT("present"),
    ABSENT("absent"),
    SICK("sick"),
    LATE("late");

    private final String value;

    AttendanceStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

