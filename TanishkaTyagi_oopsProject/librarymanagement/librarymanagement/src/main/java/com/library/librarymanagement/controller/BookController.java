package com.library.librarymanagement.controller;

import com.library.librarymanagement.model.Book;
import com.library.librarymanagement.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // POST - Add Book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    // GET - All Books
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    // GET - Book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    // PUT - Update Book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    // DELETE - Delete Book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book deleted successfully";
    }
}
