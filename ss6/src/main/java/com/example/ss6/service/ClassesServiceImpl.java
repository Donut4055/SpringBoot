package com.example.ss6.service;


import com.example.ss6.entity.ClassDTO;
import com.example.ss6.entity.Classes;
import com.example.ss6.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesRepository repository;

    @Override
    public List<ClassDTO> getAllClasses() { return repository.findAll().stream() }
            .map(c -> new ClassDTO(c.getClassId(), c.getClassName(), c.getStatus()))
            .toList(); }

    @Override
    public Classes getClassById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Classes saveClass(Classes classes) { return repository.save(classes); }

    @Override
    public Classes updateClass(Long id, Classes classes) {
        Optional<Classes> existing = repository.findById(id);
        if (existing.isPresent()) {
            Classes c = existing.get();
            c.setClassName(classes.getClassName());
            c.setStatus(classes.getStatus());
            return repository.save(c);
        }
        return null;
    }

    @Override
    public void deleteClass(Long id) { repository.deleteById(id); }
}
