package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.dto.FavoritesDTO;
import com.demo.softdreams.core.entites.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface FavoritesRepository extends  JpaRepository<Favorites, Integer> {



    @Query(value = "select f.id , u.id as userId ,b.id as targetBlog , u.avatar  , b.image as thumail\n" +
            "from favorites f left join `user` u on f.user_id = u.id\n" +
            "left join blog b on f.target = b.id \n" +
            "where u.id = (:userId) \n", nativeQuery = true)
    List<FavoritesDTO> findFavoritesByUserId(long userId);

    @Query("select f from Favorites f where f.userId = ?1 and f.targetBlog = ?2")
    Favorites findByUserIdAndTargetBlog(Long userId, Long targetBlog);

    @Query("select (count(f) > 0) from Favorites f where f.userId = ?1 and f.targetBlog = ?2")
    boolean existsByUserIdAndTargetBlog(Long userId, Long targetBlog);

//    Favorites findByUserIdAndTargetBlog(Long userId, Long targetBlog);

















}