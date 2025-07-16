package com.example.ss6.service;


import com.example.ss6.entity.Classes;
import com.example.ss6.entity.Students;
import com.example.ss6.repository.ClassesRepository;
import com.example.ss6.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Students getStudentById(Long id) {
        return studentsRepository.findById(id).orElse(null);
    }

    @Override
    public Students saveStudent(Students students) {
        if (students.getClasses() != null && students.getClasses().getClassId() != null) {
            Classes cls = classesRepository.findById(students.getClasses().getClassId()).orElse(null);
            students.setClasses(cls);
        }
        return studentsRepository.save(students);
    }

    @Override
    public Students updateStudent(Long id, Students students) {
        Optional<Students> existing = studentsRepository.findById(id);
        if (existing.isPresent()) {
            Students s = existing.get();
            s.setFullName(students.getFullName());
            s.setGender(students.getGender());
            s.setBirthday(students.getBirthday());
            s.setAddress(students.getAddress());
            if (students.getClasses() != null && students.getClasses().getClassId() != null) {
                Classes cls = classesRepository.findById(students.getClasses().getClassId()).orElse(null);
                s.setClasses(cls);
            }
            return studentsRepository.save(s);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public List<Students> getStudentsByClassId(Long classId) {
        return studentsRepository.findByClasses_ClassId(classId);
    }
}
