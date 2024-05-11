package com.demo.softdreams.administrator.dto;

import com.demo.softdreams.administrator.dto.blog.BlogReport;
import com.demo.softdreams.administrator.dto.blog.BlogsReport;
import com.demo.softdreams.core.dto.UserDTO;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.shared.respone.ReportBlog;

public class BlogMapper {
    public static ReportBlog toBlogReport(BlogReport blog) {
        ReportBlog items = ReportBlog.builder()
                .blogTitle(blog.getTitle())
                .totalView(blog.getView())
                .totalComment(blog.getComment())
                .build();
        return items;
    }
}
