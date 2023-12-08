package com.example.LibraryProject;

import com.example.LibraryProject.model.Book;
import com.example.LibraryProject.model.Borrow;
import com.example.LibraryProject.model.User;
import com.example.LibraryProject.repository.BookRepository;
import com.example.LibraryProject.repository.BorrowRepository;
import com.example.LibraryProject.repository.UserRepository;
import com.example.LibraryProject.utlity.Initialization;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository,
                                 BookRepository bookRepository,
                                 BorrowRepository borrowRepository) throws Exception {
        return (String[] args) -> {
            List<User> users = Initialization.userInitializeDataFromCsv();
            List<Book> books = Initialization.bookInitializeDataFromCsv();
            userRepository.saveAll(users);
            bookRepository.saveAll(books);
            List<Borrow> borrows = Initialization.borrowInitializeDataFromCsv(users, books);
            borrowRepository.saveAll(borrows);
        };
    }
}
