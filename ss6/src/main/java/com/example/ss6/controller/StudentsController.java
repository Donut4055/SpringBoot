package com.example.ss6.controller;


import com.example.ss6.entity.Students;
import com.example.ss6.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    @Autowired
    private StudentsService service;

    @GetMapping
    public List<Students> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Students getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public Students insertStudent(@RequestBody Students students) {
        return service.saveStudent(students);
    }

    @PutMapping("/{id}")
    public Students updateStudent(@PathVariable Long id, @RequestBody Students students) {
        return service.updateStudent(id, students);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @GetMapping("/by-class/{classId}")
    public List<Students> getStudentsByClassId(@PathVariable Long classId) {
        return service.getStudentsByClassId(classId);
    }
}

