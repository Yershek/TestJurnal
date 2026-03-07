package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.LoginRequest;
import com.example.testjurnal.dto.request.UsersDtoRequest;
import com.example.testjurnal.dto.response.UsersDtoResponse;
import com.example.testjurnal.mappers.UsersMapper;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aith")
public class AuthController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @Autowired
    public AuthController(UsersService usersService, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDtoResponse> register(@RequestBody UsersDtoRequest request){
        return ResponseEntity.ok(usersMapper.toResponse(
                usersService.register(
                        usersMapper.toEntity(request)
                ))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(
                usersService.auth(request.getUsername(), request.getPassword())
        );
    }

    @GetMapping("/get-current")
    public ResponseEntity<UsersDtoResponse> getCurrent(){
        return ResponseEntity.ok(
                usersMapper.toResponse(
                        usersService.getCurrentUser()
                )
        );
    }
}