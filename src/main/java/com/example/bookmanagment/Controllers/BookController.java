package com.example.bookmanagment.Controllers;

import com.example.bookmanagment.Entities.feature.Book;
import com.example.bookmanagment.Entities.requests.BookRequest;
import com.example.bookmanagment.Services.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void addBook(@RequestBody BookRequest request) {
            bookService.addBook(request);
    }

    @PutMapping("{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editBook(@RequestBody BookRequest request, @PathVariable Long bookId) throws BadRequestException {
        bookService.editBook(request, bookId);
    }

    @DeleteMapping("{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Book> getBooks(@RequestParam(required = false) Long category) {
         return bookService.getAllBooks(category);
    }

    @GetMapping("{bookId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Book getBookDetails(@PathVariable long bookId) {
        return bookService.getBookDetails(bookId);
    }


}
