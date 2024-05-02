package com.demo.softdreams.administrator.dto.blog;

import com.demo.softdreams.shared.res.IdReq;
import com.demo.softdreams.shared.res.IdRes;
import com.demo.softdreams.shared.service.Trimmable;
import lombok.*;

import java.util.List;
import java.util.UUID;
@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Data
public class SaveBlogReq extends IdRes implements Trimmable{

//    private String type;
    private String title;
    private String active;
    private String image;

    private String description;
    private String content;

    private Long userId;
    private List<Long> categoryList;

}
