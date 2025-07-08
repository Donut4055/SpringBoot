package com.example.ss2.service;

import com.example.ss2.entity.Showtime;
import com.example.ss2.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime, Long> {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }

    @Override
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    public List<Showtime> filterShowtimes(Long movieId, Long screenRoomId, LocalDateTime startTime) {
        if (movieId != null && screenRoomId != null && startTime != null) {
            return showtimeRepository.findByMovie_IdAndScreenRoom_IdAndStartTimeAfter(movieId, screenRoomId, startTime);
        } else if (movieId != null && screenRoomId != null) {
            return showtimeRepository.findByMovie_IdAndScreenRoom_Id(movieId, screenRoomId);
        } else if (movieId != null) {
            return showtimeRepository.findByMovie_Id(movieId);
        } else if (screenRoomId != null) {
            return showtimeRepository.findByScreenRoom_Id(screenRoomId);
        } else if (startTime != null) {
            return showtimeRepository.findByStartTimeAfter(startTime);
        } else {
            return showtimeRepository.findAll();
        }
    }

}
