package com.demo.softdreams.shared.respone;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportBlog implements Serializable {
    public String blogTitle;
    public Long totalView;
    public Long totalComment;

}
