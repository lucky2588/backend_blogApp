package com.demo.softdreams.administrator.dto.blog;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeActive {
    private Long targetBlog;

    private String active;
}
