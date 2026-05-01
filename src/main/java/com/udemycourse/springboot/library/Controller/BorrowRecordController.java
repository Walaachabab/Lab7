package com.udemycourse.springboot.library.Controller;


import com.udemycourse.springboot.library.Api.ApiResponse;
import com.udemycourse.springboot.library.Model.BorrowRecord;
import com.udemycourse.springboot.library.Service.BorrowRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrowrecords")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllRecords() {
        return ResponseEntity.status(200)
                .body(borrowRecordService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRecord(@Valid @RequestBody BorrowRecord record,
                                       Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        borrowRecordService.addRecord(record);
        return ResponseEntity.status(200)
                .body(new ApiResponse("Borrow record added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable String id,
                                          @Valid @RequestBody BorrowRecord record,
                                          Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = borrowRecordService.update(id, record);

        if (isUpdated) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Borrow record updated successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Borrow record not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable String id) {
        boolean isDeleted = borrowRecordService.delete(id);

        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Borrow record deleted successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Borrow record not found"));
    }

    //borrow Book
    @PostMapping("/borrow/{bookId}/{memberId}")
    public ResponseEntity<?> borrowBook(@PathVariable String bookId,
                                        @PathVariable String memberId) {
        boolean isBorrowed = borrowRecordService.borrowBook(bookId, memberId);

        if (isBorrowed) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Book borrowed successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Borrow failed"));
    }


// return Book
    @PutMapping("/return/{bookId}/{memberId}")
    public ResponseEntity<?> returnBook(@PathVariable String bookId,
                                        @PathVariable String memberId) {
        boolean isReturned = borrowRecordService.returnBook(bookId, memberId);

        if (isReturned) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Book returned successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Return failed"));
    }


}
