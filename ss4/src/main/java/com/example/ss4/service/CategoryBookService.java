package com.example.ss4.service;


import com.example.ss4.entity.CategoryBook;

import java.util.List;

public interface CategoryBookService {
    List<CategoryBook> findAll();
    CategoryBook findById(Long id);
    CategoryBook save(CategoryBook categoryBook);
    void deleteById(Long id);
}
