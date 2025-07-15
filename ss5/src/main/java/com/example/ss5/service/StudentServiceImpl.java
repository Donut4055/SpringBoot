package com.example.ss5.service;


import com.example.ss5.entity.Student;
import com.example.ss5.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> existing = repository.findById(id);
        if (existing.isPresent()) {
            Student s = existing.get();
            s.setFullName(student.getFullName());
            s.setGender(student.getGender());
            s.setBirthday(student.getBirthday());
            s.setAddress(student.getAddress());
            s.setClassName(student.getClassName());
            return repository.save(s);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> getStudentsByAddress(String address) {
        return repository.findByAddressContainingIgnoreCase(address);
    }

    @Override
    public List<Student> getStudentsByClassName(String className) {
        return repository.findByClassName(className);
    }
}
