package com.example.ss6.service;


import com.example.ss6.entity.Students;

import java.util.List;

public interface StudentsService {
    List<Students> getAllStudents();
    Students getStudentById(Long id);
    Students saveStudent(Students students);
    Students updateStudent(Long id, Students students);
    void deleteStudent(Long id);
    List<Students> getStudentsByClassId(Long classId);
}

