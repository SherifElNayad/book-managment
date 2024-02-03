package com.example.bookmanagment.Services;


import com.example.bookmanagment.Entities.feature.Book;
import com.example.bookmanagment.Entities.feature.Category;
import com.example.bookmanagment.Entities.requests.BookRequest;
import com.example.bookmanagment.Exceptions.BadRequestAlertException;
import com.example.bookmanagment.Exceptions.ErrorCodes;
import com.example.bookmanagment.Repos.BookRepository;
import com.example.bookmanagment.Repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public void addBook(BookRequest request){
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setAvailable(true);
        book.setQuantity(1);
        book.setShortDescription(request.getShortDescription());
        book.setPublishDate(request.getPublishDate());
        book.setCategory(category);
        bookRepository.save(book);
    }

    public void editBook(BookRequest request, Long bookId) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Book does not exist"));
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setShortDescription(request.getShortDescription());
        book.setAvailable(request.getIsAv());
        book.setPublishDate(request.getPublishDate());
        book.setCategory(category);
        bookRepository.save(book);
    }

    public List<Book> getAllBooks(Long categoryId) throws BadRequestAlertException {
        if(categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(()->new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
            return bookRepository.findByCategory(category);
        }
        else
            return bookRepository.findAll();
    }

    public Book getBookDetails(long bookId) throws BadRequestAlertException {
        return bookRepository.findById(bookId)
                .orElseThrow(()->new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));

    }

    public void decrementQuantity(Book book) {
        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
