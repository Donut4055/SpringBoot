package com.example.ss6.repository;


import com.example.ss6.entity.ClassDTO;
import com.example.ss6.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("""
SELECT new com.example.ss6.entity.ClassDTO(c.classId, c.className, c.status)
            FROM Classes c
            """)
    List<ClassDTO> findByClassName(String className);
}

