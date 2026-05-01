package com.udemycourse.springboot.library.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "ID can not be empty")
    private String id;

    @NotEmpty(message = "Title can not be empty")
    private String title;

    @NotEmpty(message = "Author can not be empty")
    @Size(min = 3, message = "Author must be at least 3 characters")
    private String author;

    @NotEmpty(message = "Category can not be empty")
    @Pattern(regexp = "^(fiction|science|history|Novels|stories)$",
            message = "Category must be fiction, science, or history")
    private String category;
    private boolean available;
}
