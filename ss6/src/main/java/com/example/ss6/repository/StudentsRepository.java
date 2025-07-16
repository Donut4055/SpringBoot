package com.example.ss6.repository;


import com.example.ss6.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    List<Students> findByClasses_ClassId(Long classId);
}

