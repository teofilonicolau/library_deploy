package com.nicolau.library.service;

import com.nicolau.library.model.Book;
import com.nicolau.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}