package com.example.ss2.service;

import com.example.ss2.entity.ScreenRoom;
import com.example.ss2.entity.Seat;
import com.example.ss2.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements IService<Seat, Long> {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }
}

