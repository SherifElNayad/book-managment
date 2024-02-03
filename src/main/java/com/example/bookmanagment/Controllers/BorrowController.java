package com.example.bookmanagment.Controllers;

import com.example.bookmanagment.Entities.feature.BorrowRequest;
import com.example.bookmanagment.Entities.requests.BorrowRequestBody;
import com.example.bookmanagment.Services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/borrow")
@RequiredArgsConstructor
public class BorrowController {

    final private BorrowService borrowService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void addRequest(@RequestBody BorrowRequestBody request,Principal principal) {
        borrowService.addRequest(request,principal);
    }

    @PutMapping("/{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void editRequest(@RequestBody BorrowRequestBody request, @PathVariable long borrowId) {
        borrowService.editRequest(request, borrowId);
    }

    @PostMapping("approve/{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void approveBorrow(@PathVariable long borrowId) {
        borrowService.handleBorrowRequest(borrowId, true);
    }

    @PostMapping("reject/{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void rejectBorrow(@PathVariable long borrowId) {
        borrowService.handleBorrowRequest(borrowId, false);
    }

    @DeleteMapping("{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteBorrow(@PathVariable long borrowId) {
        borrowService.deleteRequest(borrowId);
    }

    @GetMapping("/{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public BorrowRequest getCertainBorrowRequest(@PathVariable long borrowId, Principal principal) {

        return borrowService.getCertainBorrowRequest(borrowId, principal);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<BorrowRequest> getAllBorrowRequests(Principal principal) {
        return borrowService.getAllRequests(principal);
    }

}
