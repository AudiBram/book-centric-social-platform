package org.project.bookcentricapi.book;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.bookcentricapi.common.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> saveBook(@Valid @RequestBody BookRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.save(request, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable("book-id") Long bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) long page,
            @RequestParam(name = "size", defaultValue = "10", required = false) long size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAll(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) long page,
            @RequestParam(name = "size", defaultValue = "10", required = false) long size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) long page,
            @RequestParam(name = "size", defaultValue = "10", required = false) long size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) long page,
            @RequestParam(name = "size", defaultValue = "10", required = false) long size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, connectedUser));
    }

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Long> updateShareableStatus(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateShareableStatus(bookId, connectedUser));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Long> updateArchivedStatus(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId, connectedUser));
    }

    @PostMapping("/borrow/{book-id}")
    public ResponseEntity<Long> borrowBook(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, connectedUser));
    }

    @PatchMapping("/borrow/return/{book-id}")
    public ResponseEntity<Long> returnedBorrowedBook(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.returnBorrowedBook(bookId, connectedUser));
    }

    @PatchMapping("/borrow/return/approved/{book-id}")
    public ResponseEntity<Long> returnedApprovedReturnBorrowedBook(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.returnApprovedReturnBorrowedBook(bookId, connectedUser));
    }

    @PostMapping(value = "/cover/{book-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadBookCoverPicture(
            @PathVariable("book-id") Long bookId,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication connectedUser
    ) {
        bookService.uploadBookCoverPicture(file, connectedUser, bookId);
        return ResponseEntity.accepted().build();
    }

}
