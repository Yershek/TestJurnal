package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.GroupDtpRequest;
import com.example.testjurnal.dto.response.GroupDtoResponse;
import com.example.testjurnal.mappers.GroupMapper;
import com.example.testjurnal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    public final GroupService service;
    public final GroupMapper mapper;

    @Autowired
    public GroupController(GroupService service, GroupMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GroupDtoResponse>> getAll(){
        return ResponseEntity.ok(
                service.getAll().stream()
                        .map(mapper::toResponse).toList()
        );
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GroupDtoResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.toResponse(
                        service.getById(id)
                )
        );
    }

    @PostMapping("/save")
    public ResponseEntity<GroupDtoResponse> save(@RequestBody GroupDtpRequest request){
        return ResponseEntity.ok(
                mapper.toResponse(
                        service.save(
                                mapper.toEntity(request)
                        )
                )
        );
    }

    @PutMapping("/update")
    public ResponseEntity<GroupDtoResponse> update(@RequestBody GroupDtpRequest request){
        return ResponseEntity.ok(
                mapper.toResponse(
                        service.update(
                                mapper.toEntity(request)
                        )
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("it is deleting");
    }
}
