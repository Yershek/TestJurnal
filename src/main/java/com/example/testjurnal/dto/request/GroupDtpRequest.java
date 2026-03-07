package com.example.testjurnal.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDtpRequest {
    private String name;
    private String facultyName;
    private Integer courseNumber;
}
