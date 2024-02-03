package com.example.bookmanagment.Services;

import com.example.bookmanagment.Entities.feature.Book;
import com.example.bookmanagment.Entities.feature.BorrowRequest;
import com.example.bookmanagment.Entities.requests.BorrowRequestBody;
import com.example.bookmanagment.Exceptions.BadRequestAlertException;
import com.example.bookmanagment.Exceptions.ErrorCodes;
import com.example.bookmanagment.Repos.BookRepository;
import com.example.bookmanagment.Repos.BorrowRepository;
import com.example.bookmanagment.Repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowService {
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public void addRequest(BorrowRequestBody request, Principal principal)  {
        var user = userRepository.findByEmail((principal.getName()))
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.AUTHENTICATION_ERROR, "You Must Be Authenticated"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
            if (book.getQuantity() > 0 && borrowRepository.findByBookAndUser(book, user).isEmpty()) {
            BorrowRequest borrowRequest = new BorrowRequest();
            borrowRequest.setApproved(false);
            borrowRequest.setBook(book);
            borrowRequest.setEndDate(request.getEndDate());
            borrowRequest.setStartDate(request.getStartDate());
            borrowRequest.setUser(user);
            borrowRepository.save(borrowRequest);
        } else {
            throw new BadRequestAlertException(ErrorCodes.OWNERSHIP_ERROR,"Book is not available for borrowing");
        }
    }

    public List<BorrowRequest> getAllRequests(Principal principal) {
        var user = userRepository.findByEmail((principal.getName()))
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.AUTHENTICATION_ERROR, "You Must Be Authenticated"));
        if (user.getRole().name().equals("USER")) {
            return borrowRepository.findByUser(user);
        }
        return borrowRepository.findAll();
    }

    public void editRequest(BorrowRequestBody request, long borrowId) throws BadRequestAlertException {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
        BorrowRequest borrowRequest = borrowRepository.getReferenceById(borrowId);
        borrowRequest.setStartDate(request.getStartDate());
        borrowRequest.setEndDate(request.getEndDate());
        borrowRequest.setId(borrowId);
        borrowRequest.setBook(book);
        borrowRepository.save(borrowRequest);
    }

    public void handleBorrowRequest(long borrowId, boolean borrowed) {
        BorrowRequest borrowRequest = borrowRepository.getReferenceById(borrowId);
        if (borrowed) {
            Book book = borrowRequest.getBook();
            if (book.getQuantity() > 0) {
                bookService.decrementQuantity(book);
                log.info("Borrow request has been Accepted");
                borrowRequest.setApproved(true);
            } else {
                log.info("Book '{}' quantity is 0", book.getTitle());
                throw new BadRequestAlertException(ErrorCodes.OWNERSHIP_ERROR,"Book is not available for borrowing");
            }
        }
    }

    public void deleteRequest(long borrowId) {
        borrowRepository.deleteById(borrowId);
    }

    public BorrowRequest getCertainBorrowRequest(long borrowId, Principal principal) {
        var user = userRepository.findByEmail((principal.getName()))
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.AUTHENTICATION_ERROR,"You Must Be Authenticated"));
        if (user.getRole().name().equals("USER")) {
            return borrowRepository.findByIdAndUser(borrowId,user)
                    .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.OWNERSHIP_ERROR,"You don't own this borrow request"));

        }
        return borrowRepository.findById(borrowId).get();
    }
}
