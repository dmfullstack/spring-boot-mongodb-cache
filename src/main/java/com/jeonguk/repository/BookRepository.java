package com.jeonguk.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jeonguk.document.Book;

public interface BookRepository extends MongoRepository<Book, String> {

  Book findByTitle(String title);
  Optional<Book> findById(String id);
  void deleteById(String id);
  
}
