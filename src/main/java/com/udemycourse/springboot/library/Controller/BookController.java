package com.udemycourse.springboot.library.Controller;


import com.udemycourse.springboot.library.Api.ApiResponse;
import com.udemycourse.springboot.library.Model.Book;
import com.udemycourse.springboot.library.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.status(200).body(bookService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        bookService.addBook(book);
        return ResponseEntity.status(200)
                .body(new ApiResponse("Book added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id,
                                        @Valid @RequestBody Book book,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = bookService.update(id, book);

        if (isUpdated) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Book updated successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Book not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        boolean isDeleted = bookService.delete(id);

        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Book deleted successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Book not found"));
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        return ResponseEntity.status(200)
                .body(bookService.getAvailableBooks());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        return ResponseEntity.status(200)
                .body(bookService.getByCategory(category));
    }
}
