package com.example.LibraryProject.utlity;

import com.example.LibraryProject.model.Book;
import com.example.LibraryProject.model.Borrow;
import com.example.LibraryProject.model.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Initialization {
    public static List<User> userInitializeDataFromCsv() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input/user.csv"))) {
            reader.readLine();
            String line;
            while (!(line = reader.readLine()).isEmpty()) {
                String[] data = line.split(",");

                User user = new User();
                user.setName(data[0]);
                user.setFirstName(data[1]);
                user.setMemberSince(data[2].isEmpty() ? null : convertStringToDate(data[2]));
                user.setMemberTill(data[3].isEmpty() ? null : convertStringToDate(data[3]));
                user.setGender(data[4]);

                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Book> bookInitializeDataFromCsv() {
        List<Book> books = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader("src/main/resources/input/books.csv"))
                .withSkipLines(1).build()) {

            List<String[]> rows = csvReader.readAll();
            List<String[]> filteredRows = rows
                    .stream()
                    .filter(row -> row.length == 4 && Arrays.stream(row).anyMatch(s -> !s.trim().isEmpty()))
                    .toList();

            for (String[] data : filteredRows) {
                Book book = new Book();
                book.setTitle(data[0]);
                book.setAuthor(data[1]);
                book.setGenre(data[2]);
                book.setPublisher(data[3]);

                books.add(book);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static List<Borrow> borrowInitializeDataFromCsv(List<User> users, List<Book> books) {
        List<Borrow> borrows = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader("src/main/resources/input/borrowed.csv"))
                .withSkipLines(1).build()) {

            List<String[]> rows = csvReader.readAll();
            List<String[]> filteredRows = rows
                    .stream()
                    .filter(row -> row.length == 4 && Arrays.stream(row).anyMatch(s -> !s.trim().isEmpty()))
                    .toList();

            for (String[] data : filteredRows) {
                Borrow borrow = new Borrow();
                borrow.setBorrower(data[0]);
                borrow.setBookName(data[1]);
                borrow.setBorrowedFrom(data[2].isEmpty() ? null : convertStringToDate(data[2]));
                borrow.setBorrowedTo(data[3].isEmpty() ? null : convertStringToDate(data[3]));

                User user = users.stream()
                        .filter(u -> u.getName().concat(",").concat(u.getFirstName()).equals(borrow.getBorrower()))
                        .findFirst().get();
                borrow.setUser(user);

                Book book = books.stream()
                        .filter(b -> b.getTitle().equals(borrow.getBookName())).findFirst().get();
                borrow.setBook(book);

                borrows.add(borrow);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    private static LocalDate convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
