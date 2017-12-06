package com.jeonguk;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongodbCacheApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractMongoDBRedisTest {

}
