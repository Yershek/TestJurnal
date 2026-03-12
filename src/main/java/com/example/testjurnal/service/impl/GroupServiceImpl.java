package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Group;
import com.example.testjurnal.repository.GroupRepository;
import com.example.testjurnal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    public final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group save(Group e) {
        return groupRepository.save(e);
    }

    @Override
    public Group getById(Long id) {
        System.out.println(id);
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found group by id"));
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group update(Group e) {
        return groupRepository.findById(e.getId())
                .map(exist -> groupRepository.save(e))
                .orElseThrow(() -> new RuntimeException("Not found group by id"));
    }

    @Override
    public void delete(Long id) {
        groupRepository.delete(
                groupRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Not found group by id"))
        );
    }
}
