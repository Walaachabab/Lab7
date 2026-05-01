package com.udemycourse.springboot.library.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Librarian {

    @NotEmpty(message = "ID can not be empty")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3 ,message = "Name must be at least 3 characters")
     private String name;

    @NotEmpty(message = "Shift can not be empty")
    @Pattern(regexp = "^(morning|evening)$",
            message = "Shift must be morning or evening")
    private String shift;
}
