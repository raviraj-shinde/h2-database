package com.raviraj.H2Demo.repository;

import com.raviraj.H2Demo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
