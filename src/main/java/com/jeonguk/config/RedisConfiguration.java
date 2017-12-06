package com.jeonguk.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

  @Bean
  @Override
  public CacheErrorHandler errorHandler() {
      return new CustomCacheErrorHandler();
  }

}
