package com.example.ss6.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuId;

    private String fullName;
    private String gender;
    private LocalDate birthday;
    private String address;

    @ManyToOne
    @JoinColumn(name = "classId")
    private Classes classes;

    public Students() {
    }

    public Students(Long stuId, String fullName, String gender, LocalDate birthday, String address, Classes classes) {
        this.stuId = stuId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.classes = classes;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
