package com.example.ss4.controller;

import com.example.ss4.entity.CategoryBook;
import com.example.ss4.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorybooks")
public class CategoryBookController {
    @Autowired
    private CategoryBookService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categoryBooks", service.findAll());
        return "categorybook/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("categoryBook", service.findById(id));
        return "categorybook/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("categoryBook", new CategoryBook());
        return "categorybook/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CategoryBook categoryBook) {
        service.save(categoryBook);
        return "redirect:/categorybooks";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoryBook", service.findById(id));
        return "categorybook/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/categorybooks";
    }
}
