package com.example.testjurnal.service;

import com.example.testjurnal.entity.Group;

import java.util.List;

public interface GroupService {
    Group save(Group e);
    Group getById(Long id);
    List<Group> getAll();
    Group update(Group e);
    void delete(Long id);
}
