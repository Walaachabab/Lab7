package com.udemycourse.springboot.library.Controller;
import com.udemycourse.springboot.library.Api.ApiResponse;
import com.udemycourse.springboot.library.Model.Member;
import com.udemycourse.springboot.library.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // GET
    @GetMapping("/get")
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.status(200).body(memberService.getAll());
    }

    // ADD
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        memberService.addMember(member);
        return ResponseEntity.status(200)
                .body(new ApiResponse("Member added successfully"));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMember(@PathVariable String id,
                                          @Valid @RequestBody Member member,
                                          Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = memberService.update(id, member);

        if (isUpdated) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Member updated successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Member not found"));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {

        boolean isDeleted = memberService.delete(id);

        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ApiResponse("Member deleted successfully"));
        }

        return ResponseEntity.status(400)
                .body(new ApiResponse("Member not found"));
    }

    // LOGIC
    @GetMapping("/borrowed")
    public ResponseEntity<?> getMembersWithBorrowedBooks() {
        return ResponseEntity.status(200)
                .body(memberService.getMembersWithBorrowedBooks());
    }
}
