package com.jeonguk.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jeonguk.controller.MongoDBRedisController;
import com.jeonguk.document.Book;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

  private final MongoDBRedisController controller;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final Long cnt = controller.count();
    if (cnt == 0) {
      controller.deleteCache();
      final Book book = new Book();
      book.setTitle("MongoDbCookBook");
      book.setText("MongoDB Data Book");
      book.setAuthor("Raja");
      controller.saveBook(book);
    }
  }

}
