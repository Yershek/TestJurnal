package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.GroupDtpRequest;
import com.example.testjurnal.dto.response.GroupDtoResponse;
import com.example.testjurnal.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Group toEntity(GroupDtpRequest request){
        return Group.builder()
                .name(request.getName())
                .facultyName(request.getFacultyName())
                .courseNumber(request.getCourseNumber())
                .build();
    }

    public GroupDtoResponse toResponse(Group group){
        return GroupDtoResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .facultyName(group.getFacultyName())
                .courseNumber(group.getCourseNumber())
                .createdAt(group.getCreatedAt())
                .build();
    }
}

