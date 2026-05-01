package com.udemycourse.springboot.library.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowRecord {

    @NotEmpty(message = "ID can not be empty")
    private String id;

    @NotEmpty(message = "Book ID can not be empty")
    private String bookId;

    @NotEmpty(message = "Member ID can not be empty")
    private String memberId;

    @NotEmpty(message = "Librarian ID can not be empty")
    private String librarianId;

    @NotNull(message = "Borrow Date cannot be null")
    private LocalDate borrowDate;

    @NotNull(message = "Return Date cannot be null")
    private LocalDate returnDate;

}
