package com.nicolau.library.controller;

import com.nicolau.library.exception.ResourceNotFoundException;
import com.nicolau.library.model.Book;
import com.nicolau.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookService.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        book.setId(id);
        bookService.saveBook(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}