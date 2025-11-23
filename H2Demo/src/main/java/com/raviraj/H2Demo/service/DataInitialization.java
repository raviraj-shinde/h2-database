package com.raviraj.H2Demo.service;

import com.raviraj.H2Demo.model.Book;
import com.raviraj.H2Demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {
    //We've already done using schema.sql
    //DataInitialization:- using CommandLineRunner

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        bookRepository.save(
                Book.builder()
                .title("Days Gone :: From CommandLineRunner")
                .build()
        );

        System.out.println("Days Gone :: From CommandLineRunner");
    }
}
