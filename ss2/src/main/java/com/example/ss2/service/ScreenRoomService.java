package com.example.ss2.service;

import com.example.ss2.entity.Movie;
import com.example.ss2.entity.ScreenRoom;
import com.example.ss2.repository.ScreenRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScreenRoomService implements IService<ScreenRoom, Long> {

    @Autowired
    private ScreenRoomRepository screenRoomRepository;

    @Override
    public ScreenRoom save(ScreenRoom screenRoom) {
        return screenRoomRepository.save(screenRoom);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public ScreenRoom update(ScreenRoom screenRoom) {
        return screenRoomRepository.save(screenRoom);
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.deleteById(id);
    }

    @Override
    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();
    }
}

