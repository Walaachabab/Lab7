package com.udemycourse.springboot.library.Service;


import com.udemycourse.springboot.library.Model.Librarian;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LibrarianService {
    ArrayList<Librarian> librarians = new ArrayList<>();

    // GET
    public ArrayList<Librarian> getAll() {
        return librarians;
    }

    // ADD
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    // UPDATE
    public boolean update(String id, Librarian librarian) {
        for (int i = 0; i < librarians.size(); i++) {
            if (librarians.get(i).getId().equals(id)) {
                librarian.setId(id);
                librarians.set(i, librarian);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(String id) {
        for (int i = 0; i < librarians.size(); i++) {
            if (librarians.get(i).getId().equals(id)) {
                librarians.remove(i);
                return true;
            }
        }
        return false;
    }
}
