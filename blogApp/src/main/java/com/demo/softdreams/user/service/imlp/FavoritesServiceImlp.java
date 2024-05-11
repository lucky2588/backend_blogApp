package com.demo.softdreams.user.service.imlp;


import com.demo.softdreams.core.entites.Favorites;

import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.respository.FavoritesRepository;
import com.demo.softdreams.user.service.FavoritesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
@Slf4j
public class FavoritesServiceImlp implements FavoritesService {
    private final String KEY = "favorites";
    private final ObjectMapper objectMapper;

    @Autowired
    private FavoritesRepository favoritesRepository;


    @Override
    @Cacheable(value = "favorites",key = "#userId.toString()+#targetBlog.toString()",unless="#result == null")
    public Favorites getFavoritesDetail(Long userId , Long targetBlog) {
        return favoritesRepository.findByUserIdAndTargetBlog(userId,targetBlog);
    }

    @Override
    @Caching(
//            evict = {@CacheEvict(value = "favorites", allEntries = true)}, //remove cache old
            cacheable = {@Cacheable(value = "favorites",key = "#userId.toString()+#targetBlog.toString()",unless="#result == null")}
             ,put = {@CachePut(value = "favorites",key = "#userId.toString()+#targetBlog.toString()"),
            } // push or update cache by favoritesId

    )
    public Favorites saveFavorites(Long userId , Long targetBlog){
      if(!favoritesRepository.existsByUserIdAndTargetBlog(userId,targetBlog)){
          Favorites favorites = new Favorites();
          favorites.setUserId(userId);
          favorites.setTargetBlog(targetBlog);
          return favoritesRepository.save(favorites);
      }else{
          throw new BadResquestException("can't add favorites");
      }
    }
}
