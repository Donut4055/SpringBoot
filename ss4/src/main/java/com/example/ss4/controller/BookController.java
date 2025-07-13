package com.example.ss4.controller;


import com.example.ss4.entity.Book;
import com.example.ss4.entity.CategoryBook;
import com.example.ss4.service.BookService;
import com.example.ss4.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categoryBooks", categoryBookService.findAll());
        return "book/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        Long cateBookId = book.getCategoryBook().getCateBookId();
        CategoryBook categoryBook = categoryBookService.findById(cateBookId);
        book.setCategoryBook(categoryBook);
        bookService.save(book);
        return "redirect:/books";
    }


    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("categoryBooks", categoryBookService.findAll());
        return "book/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
