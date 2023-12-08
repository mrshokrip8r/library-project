package com.example.LibraryProject.repository;

import com.example.LibraryProject.model.Book;
import com.example.LibraryProject.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
