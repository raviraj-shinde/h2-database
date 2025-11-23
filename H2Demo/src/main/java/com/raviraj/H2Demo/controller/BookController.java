package com.raviraj.H2Demo.controller;

import com.raviraj.H2Demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(bookRepository.findAll());
    }
}
