package com.udemycourse.springboot.library.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    @NotEmpty(message = "ID can not be empty")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 0, message = "Borrowed books cannot be negative")
    private int borrowedBooks;
}
