package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("bookPublicationYear"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("bookPublicationYear"))).getContent();
        } else return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();

    }

    public Book findOne(int id) {
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByBookTitleStartingWith(query);
    }


    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> showBooks(Person person) {
        return booksRepository.findByOwner(person);
    }

    @Transactional
    public void release(int bookId) {
        Book updatedBook = booksRepository.findById(bookId).orElse(null);
        updatedBook.setOwner(null);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void assign(Person person, int bookId) {
        Book updatedBook = booksRepository.findById(bookId).orElse(null);
        updatedBook.setOwner(person);
        booksRepository.save(updatedBook);
    }


}
