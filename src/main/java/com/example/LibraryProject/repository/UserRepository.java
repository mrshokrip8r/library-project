package com.example.LibraryProject.repository;

import com.example.LibraryProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join u.borrowList b")
    List<User> getUsersBorrowedBook();

    @Query("select u from User u left join u.borrowList b where (u.memberTill is null or u.memberTill > current_date) and" +
            " b.borrowedTo < current_date")
    List<User> getActiveUsersWithoutBorrow();

    @Query("select u from User u join u.borrowList b where b.borrowedFrom = :borrowedFrom")
    List<User> getUsersBorrowedBookOnFromDate(@Param("borrowedFrom") LocalDate borrowedFrom);
}
