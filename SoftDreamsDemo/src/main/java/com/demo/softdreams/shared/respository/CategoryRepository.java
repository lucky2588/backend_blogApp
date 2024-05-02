package com.demo.softdreams.shared.respository;

import com.demo.softdreams.administrator.dto.other.CategoryDTO;
import com.demo.softdreams.core.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByIdAndDeleted(Long id, boolean deleted);

    List<Category> findByDeleted(boolean deleted);




   @Query("select c from Category c")
    List<Category> getList();

    Set<Category> findAllByIdInAndDeleted(List<Long> categories, boolean notDelete);
}