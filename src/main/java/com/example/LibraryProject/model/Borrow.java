package com.example.LibraryProject.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowed")
public class Borrow {
    private Long id;
    private User user;
    private Book book;
    private String borrower;
    private String bookName;
    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getBorrowedFrom() {
        return borrowedFrom;
    }

    public void setBorrowedFrom(LocalDate borrowedFrom) {
        this.borrowedFrom = borrowedFrom;
    }

    public LocalDate getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrowedTo(LocalDate borrowedTo) {
        this.borrowedTo = borrowedTo;
    }
}
