package com.example.ss2.repository;

import com.example.ss2.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovie_Id(Long movieId);
    List<Showtime> findByScreenRoom_Id(Long screenRoomId);
    List<Showtime> findByStartTimeAfter(LocalDateTime startTime);
    List<Showtime> findByMovie_IdAndScreenRoom_Id(Long movieId, Long screenRoomId);
    List<Showtime> findByMovie_IdAndScreenRoom_IdAndStartTimeAfter(Long movieId, Long screenRoomId, LocalDateTime startTime);
}
