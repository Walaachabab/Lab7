package com.udemycourse.springboot.library.Service;

import com.udemycourse.springboot.library.Model.Book;
import com.udemycourse.springboot.library.Model.BorrowRecord;
import com.udemycourse.springboot.library.Model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowRecordService {
    ArrayList<BorrowRecord> records = new ArrayList<>();

    private final BookService bookService;
    private final MemberService memberService;


    // GET
    public ArrayList<BorrowRecord> getAll() {
        return records;
    }

    // ADD
    public void addRecord(BorrowRecord record) {
        records.add(record);
    }

    // UPDATE
    public boolean update(String id, BorrowRecord record) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId().equals(id)) {

                record.setId(id);
                records.set(i, record);

                return true;
            }
        }

        return false;
    }

    // DELETE
    public boolean delete(String id) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId().equals(id)) {
                records.remove(i);
                return true;
            }
        }

        return false;
    }

    //borrowBook

    public boolean borrowBook(String bookId, String memberId) {

        for (Book b : bookService.getAll()) {
            if (b.getId().equals(bookId) && b.isAvailable()) {

                b.setAvailable(false);

                for (Member m : memberService.getAll()) {
                    if (m.getId().equals(memberId)) {

                        m.setBorrowedBooks(m.getBorrowedBooks() + 1);

                        BorrowRecord record = new BorrowRecord(
                                UUID.randomUUID().toString(),
                                bookId,
                                memberId,
                                null,
                                LocalDate.now(),
                                null
                        );

                        records.add(record);

                        return true;
                    }
                }
            }
        }

        return false;
    }

//return Book

    public boolean returnBook(String bookId, String memberId) {

        for (Book b : bookService.getAll()) {
            if (b.getId().equals(bookId) && !b.isAvailable()) {

                b.setAvailable(true);

                for (Member m : memberService.getAll()) {
                    if (m.getId().equals(memberId)) {

                        if (m.getBorrowedBooks() > 0) {
                            m.setBorrowedBooks(m.getBorrowedBooks() - 1);
                        }

                        for (BorrowRecord r : records) {
                            if (r.getBookId().equals(bookId)
                                    && r.getMemberId().equals(memberId)
                                    && r.getReturnDate() == null) {

                                r.setReturnDate(LocalDate.now());
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }



















}
