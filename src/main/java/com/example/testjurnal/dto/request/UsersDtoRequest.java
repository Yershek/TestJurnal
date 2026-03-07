package com.example.testjurnal.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoRequest {
    private String username;
    private String password;
    private Long role;
    private String groupId;
}
