package com.udemycourse.springboot.library.Controller;


import com.udemycourse.springboot.library.Api.ApiResponse;
import com.udemycourse.springboot.library.Model.Librarian;
import com.udemycourse.springboot.library.Service.LibrarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/librarians")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    // GET
    @GetMapping("/get")
    public ResponseEntity<?> getAllLibrarians() {
        return ResponseEntity.status(200)
                .body(librarianService.getAll());
    }

    // ADD
    @PostMapping("/add")
    public ResponseEntity<?> addLibrarian(@Valid @RequestBody Librarian librarian,
                                          Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        librarianService.addLibrarian(librarian);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Librarian added successfully"));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLibrarian(@PathVariable String id,
                                             @Valid @RequestBody Librarian librarian,
                                             Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = librarianService.update(id, librarian);

        if (isUpdated) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Librarian updated successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Librarian not found"));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLibrarian(@PathVariable String id) {

        boolean isDeleted = librarianService.delete(id);

        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Librarian deleted successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Librarian not found"));
    }
}
