package com.demo.softdreams.user.service;

import com.demo.softdreams.core.entites.Favorites;
import com.demo.softdreams.core.exception.BadResquestException;
import org.springframework.stereotype.Service;

@Service
public interface FavoritesService {





   Favorites getFavoritesDetail(Long userId , Long targetBlog) throws BadResquestException;

    Favorites saveFavorites(Long userId , Long targetBlog)throws BadResquestException;
}
