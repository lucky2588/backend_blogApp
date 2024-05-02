package com.demo.softdreams.shared.respository;

import com.demo.softdreams.core.entites.Comments;
import com.demo.softdreams.global.entites.dto.CommentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Comments findByTargetIdAndDeleted(Long targetId, Boolean deleted);



    @Query(
            nativeQuery = true,
            value = "select c.id , c.content , c.created_date as CreatedDate, u.username as username , u.avatar " +
                    "from comments c " +
                    "left join blog b on c.target_id = b.id "+
                    "left join `user` u on c.user_id = u.id "+
                    "where c.deleted = false and c.id = :id "
    )
    CommentDetail findDetailById(Long id);





}