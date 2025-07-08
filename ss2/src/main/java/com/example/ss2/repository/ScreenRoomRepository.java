package com.example.ss2.repository;

import com.example.ss2.entity.ScreenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScreenRoomRepository extends JpaRepository<ScreenRoom, Long> {
    List<ScreenRoom> findByNameContainingIgnoreCase(String name);

    List<ScreenRoom> findByTheaterId(Long theaterId);
}
