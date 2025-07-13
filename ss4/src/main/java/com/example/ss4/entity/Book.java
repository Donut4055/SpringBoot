package com.example.ss4.entity;

import com.example.ss4.entity.CategoryBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String author;
    private String publisher;
    private Integer yearPublish;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cateBookId")
    private CategoryBook categoryBook;

}
