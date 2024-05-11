package com.demo.softdreams.shared.service;

import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
import com.demo.softdreams.shared.respone.LoginResquest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface BaseRedisService {


    void set(String key, String value); // set Key/Value for Redis

    void setTimeToLive(String key, long timeoutInMinutes); // set time of Cache Redis .

    void hashSet(String key, String field, Object value);

    boolean hashExists(String key, String field); // check Key / Filed in Redis

    Object get(String key); // get value Obj

    public Map<String, Object> getField(String key);

    Object hashGet(String key, String field);

    List<Object> hashGetByFieldPrefix(String key, String filedPrefix);

    Set<String> getFieldPrefixes(String key);

    void delete(String key);

    void delete(String key, String field);

    void delete(String key, List<String> fields);




}
