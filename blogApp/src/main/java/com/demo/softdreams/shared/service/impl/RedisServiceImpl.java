//package com.demo.softdreams.shared.service.impl;
//
//import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
//
//import com.demo.softdreams.shared.service.BaseRedisService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@Service
//
//public class RedisServiceImpl implements BaseRedisService {
//
//    private final RedisTemplate<String , Object> redisTemplate; // interact with set two filed
//    private final HashOperations<String , String , Object> hashOperations;
//
//    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Override
//    public void set(String key, String value) {
//        redisTemplate.opsForValue().set(key,value);
//    }
//
//    @Override
//    public void setTimeToLive(String key, long timeoutInMinutes) {
//        redisTemplate.expire(key,timeoutInMinutes, TimeUnit.MINUTES);
//    }
//
//    @Override
//    public void hashSet(String key, String field, Object value) {
//          hashOperations.put(key , field, value);
//    }
//
//    @Override
//    public boolean hashExists(String key, String field) {
//        return hashOperations.hasKey(key,field);
//    }
//
//    @Override
//    public Object get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    @Override
//    public Map<String, Object> getField(String key) {
//        return hashOperations.entries(key);
//    }
//
//    @Override
//    public Object hashGet(String key, String field) {
//       return hashOperations.get(key,field);
//    }
//
//    @Override
//    public List<Object> hashGetByFieldPrefix(String key, String filedPrefix) { // get list base prefix
//        List<Object> objects = new ArrayList<>();
//        Map<String, Object> hashEntries = hashOperations.entries(key);
//        for (Map.Entry<String, Object> entry : hashEntries.entrySet()) {
//            if (entry.getKey().startsWith(filedPrefix)) {
//                objects.add(entry.getValue());
//            }
//        }
//        return objects;
//    }
//
//    @Override
//    public Set<String> getFieldPrefixes(String key) {
//        return hashOperations.entries(key).keySet();
//    }
//
//    @Override
//    public void delete(String key) { // delete
//        redisTemplate.delete(key);
//    }
//
//    @Override
//    public void delete(String key, String field) { // delete entity
//            hashOperations.delete(key, field);
//    }
//
//    @Override
//    public void delete(String key, List<String> fields) { // delete list entites
//        for (String field : fields) {
//            hashOperations.delete(key, field);
//        }
//    }
//
//
//
//
//}
//
