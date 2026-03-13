package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Schedule;
import com.example.testjurnal.repository.ScheduleRepository;
import com.example.testjurnal.service.GroupService;
import com.example.testjurnal.service.ScheduleService;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupService groupService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, GroupService groupService) {
        this.scheduleRepository = scheduleRepository;
        this.groupService = groupService;
    }


    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleByDateAndGroup(Long groupId, LocalDate time) {
        System.out.println("Looking for schedule - groupId: " + groupId + ", date: " + time);
        
        // Ищем все расписания для этой даты и группы
        var schedules = scheduleRepository.findSchedulesByScheduleDateAndGroupId(time, groupId);
        
        if (schedules.isEmpty()) {
            // Создаем новое пустое расписание если не найдено
            Schedule newSchedule = new Schedule();
            newSchedule.setGroup(groupService.getById(groupId));
            newSchedule.setScheduleDate(time);
            newSchedule.setLessons(new java.util.ArrayList<>());
            return scheduleRepository.save(newSchedule);
        } else if (schedules.size() == 1) {
            // Возвращаем единственное расписание
            return schedules.get(0);
        } else {
            // Если найдено несколько расписаний, берем первое и удаляем дубликаты
            System.out.println("Found " + schedules.size() + " duplicate schedules, removing duplicates...");
            Schedule mainSchedule = schedules.get(0);
            
            // Удаляем дубликаты
            for (int i = 1; i < schedules.size(); i++) {
                Schedule duplicate = schedules.get(i);
                System.out.println("Removing duplicate schedule with ID: " + duplicate.getId());
                scheduleRepository.delete(duplicate);
            }
            
            return mainSchedule;
        }
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }


}
