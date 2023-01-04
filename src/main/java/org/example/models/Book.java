package org.example.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @NotEmpty(message = "Book title can`t be empty")
    @Size(min = 2, max = 150, message = "Book title should be from 2 to 200 symbols long")
    @Column(name = "book_title")
    private String bookTitle;

    @NotEmpty(message = "Book author field can`t be empty")
    @Size(min = 2, max = 150, message = "Book author should be from 2 to 200 symbols long")
    @Column(name = "book_author")
    private String bookAuthor;

    @Min(value = 1500, message = "Publication year should be greater than 1500")
    @Column(name = "book_publication_year")
    private int bookPublicationYear;

    @ManyToOne
    @JoinColumn(name = "holder_id", referencedColumnName = "person_id")
    private Person owner;


    public Book() {
    }

    public Book(String bookTitle, String bookAuthor, int bookPublicationYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublicationYear = bookPublicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPublicationYear() {
        return bookPublicationYear;
    }

    public void setBookPublicationYear(int bookPublicationYear) {
        this.bookPublicationYear = bookPublicationYear;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublicationYear=" + bookPublicationYear +
                ", owner=" + owner +
                '}';
    }
}
