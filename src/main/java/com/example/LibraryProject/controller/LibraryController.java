package com.example.LibraryProject.controller;

import com.example.LibraryProject.model.User;
import com.example.LibraryProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    @Autowired
    UserRepository userRepository;

    //a
    @GetMapping("/getUsersBorrowedBook")
    public ResponseEntity<List<String>> getUsersBorrowedBook() {
        List<User> users = userRepository.getUsersBorrowedBook();
        List<String> collect = users.stream()
                .map(user -> user.getFirstName().concat(" ").concat(user.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);

    }

    //b
    @GetMapping("/getActiveUsersWithoutBorrow")
    public ResponseEntity<List<String>> getActiveUsersWithoutBorrow() {
        List<User> users = userRepository.getActiveUsersWithoutBorrow();
        List<String> collect = users.stream()
                .map(user -> user.getFirstName().concat(" ").concat(user.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);

    }

    //c
    @GetMapping("/getUsersBorrowedBookOnFromDate/{fromDate}")
    public ResponseEntity<List<String>> getUsersBorrowedBookOnFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
        List<User> users = userRepository.getUsersBorrowedBookOnFromDate(fromDate);
        List<String> collect = users.stream()
                .map(user -> user.getFirstName().concat(" ").concat(user.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);

    }

}
