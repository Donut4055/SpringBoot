package com.example.ss4.service;


import com.example.ss4.entity.Book;
import com.example.ss4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> findByCategoryBookId(Long cateBookId) {
        return repository.findByCategoryBook_CateBookId(cateBookId);
    }
}

