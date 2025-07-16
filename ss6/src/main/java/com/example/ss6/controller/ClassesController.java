package com.example.ss6.controller;

import com.example.ss6.entity.Classes;
import com.example.ss6.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    private ClassesService service;

    @GetMapping
    public List<Classes> getAllClasses() {
        return service.getAllClasses();
    }

    @GetMapping("/{id}")
    public Classes getClassById(@PathVariable Long id) {
        return service.getClassById(id);
    }

    @PostMapping
    public Classes insertClass(@RequestBody Classes classes) {
        return service.saveClass(classes);
    }

    @PutMapping("/{id}")
    public Classes updateClass(@PathVariable Long id, @RequestBody Classes classes) {
        return service.updateClass(id, classes);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        service.deleteClass(id);
    }
}

