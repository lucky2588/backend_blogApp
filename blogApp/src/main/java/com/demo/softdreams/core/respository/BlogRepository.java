package com.demo.softdreams.core.respository;

import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.administrator.dto.blog.BlogReport;
import com.demo.softdreams.core.entites.Blog;
import com.demo.softdreams.global.entites.dto.CommentDetail;
import com.demo.softdreams.shared.respone.ReportBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {


    Blog findByIdAndDeleted(Long id, boolean deleted);

    Blog findByDeleted(boolean deleted);


    @Query("select c from Blog c")
    List<Blog> getList();

// Loi 2 phan
    @Query(value = "SELECT b.id, b.title,b.content, b.image  , b.created_date as CreatedDate , b.active, b.view, GROUP_CONCAT(c.label_name SEPARATOR ', ') AS listCategory " +
            "FROM blog b " +
            "LEFT JOIN blog_categories bc ON b.id = bc.blog_id " +
            "LEFT JOIN category c ON c.id = bc.categories_id " +
            "WHERE b.deleted = 0 " +
            "AND  " +
            "  (" +
            "    (LOWER(b.content) LIKE LOWER(CONCAT('%', :textSearch, '%'))) " +
            "          OR" +
            "    (LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%')))" +
            "  )" +
            "AND (:active is null or b.active = :active)" +
            "AND" +
            "( " +
            "      b.id in (" +
            "    select bc2.blog_id " +
            "    from blog_categories bc2 " +
            "    WHERE   bc2.categories_id in (:categories)" +
            "    group by bc2.blog_id" +
            ") )"+
            "GROUP BY b.id, b.active, b.title, b.view ", nativeQuery = true)
    List<BlogItems> findListBlogWithPagination(@Param("textSearch") String textSearch,String active,@Param("categories") List<Long> categories);


    @Query(nativeQuery = true,value = "SELECT b.id, b.title, b.active,b.content ,b.image , b.created_date as CreatedDate ,b.view, GROUP_CONCAT(IFNULL(c.label_name, '') SEPARATOR ', ') AS listCategory " +
            "FROM blog b " +
            "LEFT JOIN blog_categories bc ON b.id = bc.blog_id " +
            "LEFT JOIN category c ON c.id = bc.categories_id " +
            "WHERE b.deleted = 0 " +
            "AND  " +
            "  (" +
            "    (LOWER(b.content) LIKE LOWER(CONCAT('%', :textSearch, '%'))) " +
            "          OR" +
            "    (LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%')))" +
            "  )" +
            "AND (LOWER(b.active) LIKE LOWER(CONCAT('%', :active, '%'))) "+
            "AND" +
            "( " +
            "      b.id in ( " +
            "    select bc2.blog_id " +
            "    from blog_categories bc2 " +
            "    WHERE   bc2.categories_id in (:categories)" +
            "    group by bc2.blog_id" +
            ") ) "+
            "GROUP BY b.id, b.active, b.title, b.view",
            countQuery = "SELECT count(*)  " +
                    "FROM blog b " +
                    "LEFT JOIN blog_categories bc ON b.id = bc.blog_id " +
                    "LEFT JOIN category c ON c.id = bc.categories_id " +
                    "WHERE b.deleted = 0 " +
                    "AND  " +
                    "  (" +
                    "    (LOWER(b.content) LIKE LOWER(CONCAT('%', :textSearch, '%'))) " +
                    "          OR" +
                    "    (LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%')))" +
                    "  )" +
                    "AND (LOWER(b.active) LIKE LOWER(CONCAT('%', :active, '%'))) "+
                    "AND" +
                    "( " +
                    "      b.id in (" +
                    "    select bc2.blog_id " +
                    "    from blog_categories bc2 " +
                    "    WHERE   bc2.categories_id in (:categories)" +
                    "    group by bc2.blog_id" +
                    ") ) " +
                    "GROUP BY b.id, b.active, b.title, b.view " +
                    "  ")
    Page<BlogItems> findAllBlogWithPagination(@Param("textSearch") String textSearch,@Param("active") String active, @Param("categories") List<Long> categories, Pageable pageable);



    @Query(nativeQuery = true,value = "SELECT b.id, b.title, b.active,b.content, b.image  , b.created_date as CreatedDate ,b.view, GROUP_CONCAT(IFNULL(c.label_name, '') SEPARATOR ', ') AS listCategory " +
            "FROM blog b " +
            "LEFT JOIN blog_categories bc ON b.id = bc.blog_id " +
            "LEFT JOIN category c ON c.id = bc.categories_id " +
            "WHERE b.deleted = 0 " +
            "AND  " +
            "  (" +
            "    (LOWER(b.content) LIKE LOWER(CONCAT('%', :textSearch, '%'))) " +
            "          OR" +
            "    (LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%')))" +
            "  )" +
            "AND (LOWER(b.active) LIKE LOWER(CONCAT('%', :active, '%'))) "+
            "GROUP BY b.id, b.active, b.title, b.view",
            countQuery = "SELECT count(*) " +
                    "FROM blog b " +
                    "LEFT JOIN blog_categories bc ON b.id = bc.blog_id " +
                    "LEFT JOIN category c ON c.id = bc.categories_id " +
                    "WHERE b.deleted = 0 " +
                    "AND  " +
                    "  (" +
                    "    (LOWER(b.content) LIKE LOWER(CONCAT('%', :textSearch, '%'))) " +
                    "          OR" +
                    "    (LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%')))" +
                    "  )" +
                    "AND (LOWER(b.active) LIKE LOWER(CONCAT('%', :active, '%'))) "+
                    "GROUP BY b.id, b.active, b.title, b.view " +
                    "  ")
    Page<BlogItems> findAllBlogWithPaginationNotFilterCategory(@Param("textSearch") String textSearch,@Param("active") String active, Pageable pageable);





    @Query(nativeQuery = true,value = "select c.id , c.content , b.image , c.created_date as CreatedDate , u.avatar , u.name as Username " +
            "from comments c " +
            "left join blog b on c.target_id = b.id " +
            "left join `user` u on c.user_id = u.id " +
            "where b.id = :blogId and c.deleted = false and c.target_name = 'BLOG' " +
            "group by c.id , c.content , c.created_date , u.avatar , u.name " +
            "order by c.created_date desc ",
            countQuery = "SELECT count(*)  " +
                    "from comments c " +
                    "left join blog b on c.target_id = b.id " +
                    "left join `user` u on c.user_id = u.id " +
                    "where b.id = :blogId and c.deleted = true and c.target_name = 'BLOG' "+
                    "group by c.id , c.content , c.created_date , u.avatar , u.name " +
                    "order by c.created_date desc "
            )
    Page<CommentDetail> findAllCommentWithPagination(@Param("blogId") Long blogId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "with listComment as  (\n" +
                    " select c.target_id , count(c.target_id) as numsComment \n" +
                    "from comments c \n" +
                    "group by c.target_id , c.target_name\n" +
                    ")\n" +
                    "\n" +
                    "select b.title as title  , b.`view` as view, IFNULL(c.numsComment, 0) as comment \n" +
                    "from blog b left join listComment c on b.id  = c.target_id ")
    List<BlogReport> getReport();


}
