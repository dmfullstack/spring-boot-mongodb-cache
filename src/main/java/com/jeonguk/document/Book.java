package com.jeonguk.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
public class Book implements Serializable {

  private static final long serialVersionUID = 4418641046086502039L;

  @Id
  private String id;
  @Indexed
  private String title;
  private String author;
  private String text;
  @Version
  private int version;
  
}
