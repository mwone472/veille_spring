package com.example.demo.bootstrap;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author maye = new Author("Maye", "WONE");
        Author amir = new Author("Mamadou", "WONE");
        Book book1 = new Book("Spring Data JPA");
        Book book2 = new Book("Master JEE Development");

        maye.getBooks().add(book2);
        book2.getAuthors().add(maye);

        amir.getBooks().add(book1);
        book1.getAuthors().add(amir);

        authorRepository.save(maye);
        authorRepository.save(amir);

        bookRepository.save(book1);
        bookRepository.save(book2);

        System.out.println("Started in Bootstrap ! ");
        System.out.println("Number of books: " + bookRepository.count());
        for(Book book: bookRepository.findAll()){
//            for(Author author: book.getAuthors()){
//                System.out.println(author.getFirstName());
//            }
            System.out.println(book.getId() + " "+ book.getTitle() + " Author --> " + book.getTitle());
        }
    }
}
