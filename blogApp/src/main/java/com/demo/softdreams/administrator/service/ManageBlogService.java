package com.demo.softdreams.administrator.service;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.exception.RestControllerException;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.shared.res.PageData;

import java.util.List;

public interface ManageBlogService {
    
    

    PageData<BlogItems> findAllBlogsWithPagination(String textSearch, String active,List<Long> categoris, String sortProperty, String sortOrder, int page, int pageSize) throws  NotFoundException;

    BlogDetail saveBlog(SaveBlogReq req) throws RestControllerException, NotFoundException, BadResquestException;

    List<BlogItems> findAllBlogsWithPaginationList(String textSearch, String active, List<Long> categories);

    CustomApiResponse changeActiveBlog(Long id, Integer active) throws RestControllerException, NotFoundException, BadResquestException;


    CustomApiResponse deleteBlog(Long id) throws  NotFoundException, BadResquestException;

    BlogDetail findBlogById(Long id) throws NotFoundException, BadResquestException;
}
