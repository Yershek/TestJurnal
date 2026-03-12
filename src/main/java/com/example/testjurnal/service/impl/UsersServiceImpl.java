package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Users;
import com.example.testjurnal.repository.UsersRepository;
import com.example.testjurnal.security.JwtCore;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, JwtCore jwtCore, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtCore = jwtCore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public String auth(String name, String password) {
        Users authUser = usersRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new RuntimeException("error.authorization");
        }
        return jwtCore.jwtGenerator(authUser);
    }

    @Override
    public Users register(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll().stream()
                .filter(
                        users -> users.getGroupId().equals(getCurrentUser().getGroupId())
                ).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
