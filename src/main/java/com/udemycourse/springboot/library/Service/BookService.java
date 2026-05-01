package com.udemycourse.springboot.library.Service;



import com.udemycourse.springboot.library.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books = new ArrayList<>();

    // GET
    public ArrayList<Book> getAll() {
        return books;
    }

    // ADD
    public void addBook(Book book) {
        books.add(book);
    }

    // UPDATE
    public boolean update(String id, Book book){
        for (int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId().equals(id)){
                book.setId(id);
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(String id){
        for (int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId().equals(id)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }


    // AvailableBooks

    public ArrayList<Book> getAvailableBooks(){
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : books){

          if(b.isAvailable()){
              result.add(b);
          }
        }
        return result;

    }


// ByCategory
public ArrayList<Book> getByCategory(String category){
    ArrayList<Book> result = new ArrayList<>();
    for (Book b : books){
        if(b.getCategory().equalsIgnoreCase(category)){
            result.add(b);
        }
    }

    return result;

}






}

