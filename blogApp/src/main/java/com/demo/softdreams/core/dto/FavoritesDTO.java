package com.demo.softdreams.core.dto;

import jakarta.persistence.Cacheable;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;
@Cacheable
@org.hibernate.annotations.Cache(region = "favoriteList", usage = CacheConcurrencyStrategy.READ_WRITE)
public interface FavoritesDTO {

    Long getId();

    Long getUserId();
    Long targetBlog();


    String getAvatar();

    String getThumail();




}
