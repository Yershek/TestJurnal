package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Schedule;
import com.example.testjurnal.repository.ScheduleRepository;
import com.example.testjurnal.service.ScheduleService;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UsersService usersService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UsersService usersService) {
        this.scheduleRepository = scheduleRepository;
        this.usersService = usersService;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleByDateAndGroup(Long groupId, LocalDate time) {
        return scheduleRepository.findScheduleByScheduleDate(time)
                .filter(sh -> sh.getGroup().getId().equals(
                        usersService.getCurrentUser().getGroupId())).orElseThrow(
                        () -> new RuntimeException("Ошибка в getScheduleByDateAndGroup"));
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }


}
