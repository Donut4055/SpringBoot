package com.example.ss2.repository;
import com.example.ss2.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findBySeatNumber(String seatNumber);

    List<Seat> findByScreenRoomId(Long screenRoomId);
}

