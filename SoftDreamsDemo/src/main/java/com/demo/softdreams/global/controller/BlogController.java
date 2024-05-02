package com.demo.softdreams.global.controller;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.global.entites.dto.CommentDetail;
import com.demo.softdreams.global.service.BlogService;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.exception.RestControllerException;

import static com.demo.softdreams.shared.common.ResponseConstance.*;
import com.demo.softdreams.config.LocalDateTimeConfig;

import com.demo.softdreams.shared.res.CommentReq;
import com.demo.softdreams.shared.res.PageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/public/blog")
public class  BlogController  {
    private final LocalDateTimeConfig localDateTimeConfig;
    @Autowired
    private BlogService service;
    //    @ApiOperation(value = "Find Blogs in web admin with pagination")
    @GetMapping(value = "")
    public PageData<BlogItems> findAllBlogsWithPagination (
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam(required = false, defaultValue = "") String active,
            @RequestParam(required = false, defaultValue = "") List<Long> categories,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "created_date") String sortProperty,
            @RequestParam(required = false, defaultValue = "desc") String sortOrder) throws NotFoundException{
        PageData<BlogItems> response = service.findAllBlogsWithPagination(textSearch, active,categories, sortProperty, sortOrder, page, pageSize);
        return response;
    }

    @GetMapping(value = "getList")
    public List<BlogItems> findAllBlogsWithPagination (
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam(required = false, defaultValue = "") String active,
            @RequestParam(required = false, defaultValue = "") List<Long> categories) throws  RestControllerException, NotFoundException, BadResquestException {
        List<BlogItems> response = service.findAllBlogsWithPaginationList(textSearch, active , categories );
        return response;
    }

    @GetMapping(value = "/{id}")
    public BlogDetail findBlogById(@PathVariable Long id) throws  RestControllerException, NotFoundException, BadResquestException {
        BlogDetail response = service.findBlogById(id);
        if (response.getResponseCode() == INTERNAL || response.getResponseCode() == EXCEPTION) {
            throw new RestControllerException(response.getResponseStatus(), response.getResponseCode(), "findBlogById", response.getResponseMessage(), "/api/manage/Blog/" + id.toString(), localDateTimeConfig.getLocalTime());
        }
        return response;
    }


//    for Comments of blog
    @PostMapping(value = "postComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CommentDetail postComment(@RequestBody CommentReq req){
        return service.postComment(req);
    }

    @GetMapping(value = "{id}/getComments")
    public PageData<CommentDetail> findAllCommentsWithPagination(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
        return service.getComments(id,page,pageSize);
    }




}
