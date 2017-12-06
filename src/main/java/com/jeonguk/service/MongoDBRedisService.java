package com.jeonguk.service;

import com.jeonguk.document.Book;

public interface MongoDBRedisService {

  Long count();
  Book save(Book book);
  Book findBookByTitle(String title);
  Book updateByTitle(String title, String author);
  void deleteBook(String id);
  void deleteAllCache();
  void deleteAllCollections();
  
}
