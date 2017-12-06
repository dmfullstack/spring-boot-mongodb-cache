package com.jeonguk.service.impl;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.jeonguk.document.Book;
import com.jeonguk.repository.BookRepository;
import com.jeonguk.service.MongoDBRedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MongoDBRedisServiceImpl implements MongoDBRedisService {

  private final BookRepository repository;
  private final MongoTemplate mongoTemplate;

  @Override
  public Long count() {
      return this.repository.count();
  }
  
  @Override
  public Book save(Book book) {
      log.info("Saving book :{}", book);
      return this.repository.save(book);
  }
  
  @Override
  public Book findBookByTitle(String title) {
      log.info("Finding Book by Title :{}", title);
      return this.repository.findByTitle(title);
  }
  
  @Override
  public Book updateByTitle(String title, String author) {
      log.info("Updating Book Author by Title :{} with {}", title, author);
      final Query query = new Query(Criteria.where("title").is(title));
      final Update update = new Update().set("author", author);
      return this.mongoTemplate.findAndModify(query, update,
              new FindAndModifyOptions().returnNew(true).upsert(false), Book.class);
  }
  
  @Override
  public void deleteBook(String id) {
      log.info("deleting Books by id :{}", id);
      this.repository.deleteById(id);
  }
  
  @Override
  public void deleteAllCache() {
      log.info("Deleting Cache");
  }
  
  @Override
  public void deleteAllCollections() {
      this.repository.deleteAll();
  }
  
}
