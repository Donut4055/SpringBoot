package com.example.ss5.controller;


import com.example.ss5.entity.Student;
import com.example.ss5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public Student insertStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @GetMapping("/search/name")
    public List<Student> getStudentsByName(@RequestParam String name) {
        return service.getStudentsByName(name);
    }

    @GetMapping("/search/address")
    public List<Student> getStudentsByAddress(@RequestParam String address) {
        return service.getStudentsByAddress(address);
    }

    @GetMapping("/search/class")
    public List<Student> getStudentsByClassName(@RequestParam String className) {
        return service.getStudentsByClassName(className);
    }
}

