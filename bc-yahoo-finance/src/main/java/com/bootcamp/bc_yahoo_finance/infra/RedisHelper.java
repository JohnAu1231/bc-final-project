package com.bootcamp.bc_yahoo_finance.infra;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisHelper {

  private RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

  private ObjectMapper objectMapper = new ObjectMapper();

  public RedisHelper(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }
  // Encapsulate ResTemplate.class
  // 1. Generics <String, String>
  // 2. remove OpsForValue() -> set(), get()
  // 3. remove Initialize the object states (serializer)


  // get the string from redis
  // convert the string to object, and return

  //throw a independent exception is better, easy to find where error
  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException {
    String json = redisTemplate.opsForValue().get(key);
    return json == null ? null : this.objectMapper.readValue(json, clazz);
  }

  // Object -> String
  // put string into the redis
  public <T> void set(String key, T obj) throws JsonProcessingException {
    String json = this.objectMapper.writeValueAsString((obj));
    this.redisTemplate.opsForValue().set(key, json);
  }

  public <T> void set(String key, T obj, Duration duration) throws JsonProcessingException {
    String json = this.objectMapper.writeValueAsString((obj));
    this.redisTemplate.opsForValue().set(key, json, duration);
  }

  // RedisHelper redisHelper = new RedisHelper();
  // redisHelper.set
}
