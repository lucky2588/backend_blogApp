package com.demo.softdreams.administrator.dto.blog;

import com.demo.softdreams.core.entites.Category;
import com.demo.softdreams.shared.res.IdRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetail extends IdRes {
    private Long id;
    private Long view;
    private String title;
    private LocalDate createdDate;
    private String active;
    private String image;
    private String description;
    private String content;
    private List<Category> categories;
    private Long userId;

}