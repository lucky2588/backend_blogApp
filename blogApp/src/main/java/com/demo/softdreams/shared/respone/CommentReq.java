package com.demo.softdreams.shared.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReq {
    private Long targetId;
    private String targetName;
    private String content;
}
