package com.demo.softdreams.global.service;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.global.entites.dto.CommentDetail;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.res.CommentReq;
import com.demo.softdreams.shared.res.PageData;

import java.util.List;

public interface BlogService {

    PageData<BlogItems> findAllBlogsWithPagination(String textSearch, String active, List<Long> categories, String sortProperty, String sortOrder, int page, int pageSize) throws NotFoundException;



    List<BlogItems> findAllBlogsWithPaginationList(String textSearch, String active, List<Long> categories);

    BlogDetail findBlogById(Long id) throws NotFoundException, BadResquestException;


    PageData<CommentDetail> getComments(Long id, int page , int pageSize);


    CommentDetail postComment(CommentReq req) throws BadResquestException;
}
