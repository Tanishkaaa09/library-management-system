package com.library.librarymanagement.service;

import com.library.librarymanagement.model.Book;
import com.library.librarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Book updateBook(Long id, Book book) {
        Book existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setCopies(book.getCopies());
            return repo.save(existing);
        }
        return null;
    }

    public void deleteBook(Long id) {
        repo.deleteById(id);
    }
}
