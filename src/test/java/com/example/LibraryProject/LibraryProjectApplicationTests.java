package com.example.LibraryProject;

import com.example.LibraryProject.model.User;
import com.example.LibraryProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryProjectApplicationTests {

    @Autowired
    UserRepository userRepository;

    //a
    @Test
    void testGetUsersBorrowedBook() {
        List<User> userList = userRepository.getUsersBorrowedBook();

        assertEquals(11, userList.size());
        assertTrue(userList.stream()
                .map(User::getFirstName)
                .toList().contains("Liam"));
    }

    //b
    @Test
    void testGetActiveUsersWithoutBorrow() {
        List<User> userList = userRepository.getActiveUsersWithoutBorrow();

        assertEquals(9, userList.size());
        assertFalse(userList.stream()
                .map(User::getFirstName)
                .toList().contains("Oliver"));
    }

    //c
    @Test
    void testGetUsersBorrowedBookOnFromDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2012-12-29", formatter);

        List<User> userList = userRepository.getUsersBorrowedBookOnFromDate(localDate);

        assertEquals(2, userList.size());
        assertTrue(userList.stream()
                .map(User::getFirstName)
                .toList().contains("Sophia"));
    }
    //

}
