package com.example.ss4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateBookId;

    private String cateBookName;

    @OneToMany(mappedBy = "categoryBook", cascade = CascadeType.ALL)
    private List<Book> books;

}
