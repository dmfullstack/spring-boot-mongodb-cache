package com.jeonguk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeonguk.controller.MongoDBRedisController;
import com.jeonguk.document.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongodbCacheApplicationTests extends AbstractMongoDBRedisTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private MongoDBRedisController controller;
  
	@Test
	public void contextLoads() {
	  assertThat(mongoTemplate).isNotNull();
	}

  @Test
  public void insertData() {
      controller.deleteAll();
      final Book book = new Book();
      book.setTitle("MongoDbCookBook");
      book.setText("MongoDB Data Book");
      book.setAuthor("Raja");
      final Book response = controller.saveBook(book);
      assertThat(response).isNotNull();
      assertThat(response.getId()).isNotBlank();
      assertThat(response.getAuthor()).isEqualTo("Raja");
      final Book updatedBook = controller.updateAuthorByTitle("MongoDbCookBook", "Raja1");
      assertThat(updatedBook.getAuthor()).isEqualTo("Raja1");
      controller.deleteBookByTitle("JUNITTitle");
      final Book updatedBook1 = controller.findBookByTitle("JUNITTitle");
      assertThat(updatedBook1).isNull();
      controller.deleteBookByTitle("MongoDbCookBook");
  }
  
}
