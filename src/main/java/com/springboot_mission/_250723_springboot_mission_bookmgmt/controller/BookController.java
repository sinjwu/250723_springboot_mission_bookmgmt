package com.springboot_mission._250723_springboot_mission_bookmgmt.controller;

import com.springboot_mission._250723_springboot_mission_bookmgmt.model.Book;
import com.springboot_mission._250723_springboot_mission_bookmgmt.repository.AuthorRepository;
import com.springboot_mission._250723_springboot_mission_bookmgmt.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @GetMapping
    public String list() {
        return "books";
    }
    @GetMapping("/books/new")
    public String form(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.finalAll());
        return "book_form";
    }
    @PostMapping("/books")
    public String save(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }
    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Long id) {
        bookRepository.delete(id);
        return "redirect:/";
    }
}
