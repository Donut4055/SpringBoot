package com.example.ss4.repository;


import com.example.ss4.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryBook_CateBookId(Long cateBookId);
}
