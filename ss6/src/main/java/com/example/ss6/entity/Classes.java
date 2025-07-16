package com.example.ss6.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String className;
    private String status;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Students> students;

    public Classes() {
    }

    public Classes(Long classId, String className, String status) {
        this.classId = classId;
        this.className = className;
        this.status = status;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<Students> getStudents() {
        return students;
    }
    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
