package com.example.ss4.service;


import com.example.ss4.entity.CategoryBook;
import com.example.ss4.repository.CategoryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryBookServiceImpl implements CategoryBookService {
    @Autowired
    private CategoryBookRepository repository;

    @Override
    public List<CategoryBook> findAll() {
        return repository.findAll();
    }

    @Override
    public CategoryBook findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CategoryBook save(CategoryBook categoryBook) {
        return repository.save(categoryBook);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
