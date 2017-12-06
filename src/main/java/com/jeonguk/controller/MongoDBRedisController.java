package com.jeonguk.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeonguk.document.Book;
import com.jeonguk.service.MongoDBRedisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class MongoDBRedisController {

  private final MongoDBRedisService service;
 
  @PostMapping(value = "/saveBook")
  public Book saveBook(@RequestBody Book book) {
      return service.save(book);
  }
  
  @GetMapping(value = "/findByTitle/{title}")
  @Cacheable(value = "book", key = "#title", unless = "#result == null")
  public Book findBookByTitle(@PathVariable String title) {
      return service.findBookByTitle(title);
  }

  @PutMapping(value = "/updateByTitle/{title}/{author}")
  @CachePut(value = "book", key = "#title")
  public Book updateAuthorByTitle(@PathVariable(value = "title") String title,
          @PathVariable(value = "author") String author) {
      return service.updateByTitle(title, author);
  }
  
  @DeleteMapping(value = "/deleteByTitle/{title}")
  @CacheEvict(value = "book", key = "#title")
  public String deleteBookByTitle(@PathVariable(value = "title") String title) {
      final Book book = this.findBookByTitle(title);
      if (null != book) {
          this.service.deleteBook(book.getId());
          return "Book with title " + title + " deleted";
      } else {
          return "Book with title " + title + " Not Found";
      }
  }
  
  @GetMapping(value = "/deleteCache")
  @CacheEvict(value = "book", allEntries = true)
  public void deleteCache() {
      this.service.deleteAllCache();
  }
  
  public Long count() {
    return this.service.count();
  } 
  
  public void deleteAll() {
    this.service.deleteAllCollections();
  }
}
