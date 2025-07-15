package com.example.ss5.service;


import com.example.ss5.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByAddress(String address);
    List<Student> getStudentsByClassName(String className);
}

