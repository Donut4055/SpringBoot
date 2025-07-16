package com.example.ss6.service;


import com.example.ss6.entity.ClassDTO;
import com.example.ss6.entity.Classes;

import java.util.List;

public interface ClassesService {
    List<ClassDTO> getAllClasses();
    Classes getClassById(Long id);
    Classes saveClass(Classes classes);
    Classes updateClass(Long id, Classes classes);
    void deleteClass(Long id);
}
