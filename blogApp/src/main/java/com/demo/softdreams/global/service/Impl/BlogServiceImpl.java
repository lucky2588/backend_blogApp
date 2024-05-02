package com.demo.softdreams.global.service.Impl;
import com.demo.softdreams.core.entites.Comments;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.core.sercurity.iCurrentImpl;
import com.demo.softdreams.global.entites.dto.CommentDetail;
import com.demo.softdreams.global.service.BlogService;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.config.LocalDateTimeConfig;
import com.demo.softdreams.config.ModelMapperConfig;
import com.demo.softdreams.core.entites.Blog;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.res.CommentReq;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.shared.res.PageData;
import com.demo.softdreams.shared.respository.BlogRepository;
import com.demo.softdreams.shared.respository.CategoryRepository;
import com.demo.softdreams.shared.respository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.softdreams.shared.common.ResponseConstance.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final CommentsRepository commentsRepository;
    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository dataSource;
    private final ModelMapperConfig mapperConfig;
    private final LocalDateTimeConfig localDateTimeConfig;
    private final iCurrentImpl iCurrentUser;




    @Override
    public PageData<BlogItems> findAllBlogsWithPagination(String textSearch, String active,List<Long> categories, String sortProperty, String sortOrder, int page, int pageSize) throws NotFoundException {
        Page<BlogItems> blogItems;
        PageData<BlogItems> response = new PageData<>();
        Pageable pageable;
        Boolean isEmptyCategoris = false;
        try {
            if(categories == null || categories.isEmpty()) isEmptyCategoris = true;
            pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(sortOrder), sortProperty));
            blogItems = isEmptyCategoris == true ? dataSource.findAllBlogWithPaginationNotFilterCategory(textSearch.trim(),active, pageable) : dataSource.findAllBlogWithPagination(textSearch.trim(),active,categories, pageable);
            log.info("Find all blogs in module manage blog successful with size = {}", blogItems.getContent().size());
            response.setData(blogItems.getContent());
            response.setTotalElements(blogItems.getTotalElements());
            response.setTotalPages(blogItems.getTotalPages());
            response.setHasNext(blogItems.hasNext());
            response.setResponseStatus(HttpStatus.OK.value());
            response.setResponseCode(FIND_BLOG_SUCCESSFUL);
            response.setResponseMessage(MSG.get(FIND_BLOG_SUCCESSFUL));
        } catch (Exception e) {
            log.warn("Error at findAllSubsWithPagination function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException("Not Found");
        }
        return response;
    }


    @Override
    public List<BlogItems> findAllBlogsWithPaginationList(String textSearch, String active, List<Long> categories) {

        log.info("List Category" +categories);
        log.info("textSearch" +textSearch);
        log.info("active" +active );

        List<BlogItems> items = dataSource.findListBlogWithPagination(textSearch,active,categories);
        log.info("Log in Find All Panion " +items);
        return items;
    }


    @Override
    public BlogDetail findBlogById(Long id) throws NotFoundException, BadResquestException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        BlogDetail response = new BlogDetail();
        Blog currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(id,SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Blog  found: {}", currentObj);
                response = mapperConfig.modelMapper().map(currentObj, BlogDetail.class);
                log.info("Cast {} to {} before return front end successful", Blog.class.getSimpleName(), BlogDetail.class.getSimpleName());
                log.info("Mapper After "+response);
                response.setResponseStatus(HttpStatus.OK.value());
                response.setResponseCode(FIND_ALL_ACTION_SUCCESSFUL);
                response.setResponseMessage(MSG.get(FIND_CATEGORY_SUCCESSFUL));
                log.info("Find blog successful");
            } else {
                log.info("blog not found");
                customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                customApiResponse.setResponseCode(NOT_FOUND_BLOG_BY_ID);
                customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_BLOG_BY_ID_MSG));
                throw new NotFoundException(MSG.get(NOT_FOUND_BLOG_BY_ID));
//                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at findDetailById function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_BLOG_BY_ID));
        }
        return response;
    }



    // service for comment blog
    @Override
    public PageData<CommentDetail> getComments(Long id, int page, int pageSize) {
        Page<CommentDetail> items;
        PageData<CommentDetail> response = new PageData<>();
        Pageable pageable;
        try {
            pageable = PageRequest.of(page, pageSize);
            items = dataSource.findAllCommentWithPagination(id,pageable);
            log.info("Find all Comments in module manage blog successful with size = {}", items.getContent().size());
            response.setData(items.getContent());
            response.setTotalElements(items.getTotalElements());
            response.setTotalPages(items.getTotalPages());
            response.setHasNext(items.hasNext());
            response.setResponseStatus(HttpStatus.OK.value());
            response.setResponseCode(FIND_BLOG_SUCCESSFUL);
            response.setResponseMessage(MSG.get(FIND_BLOG_SUCCESSFUL));
        } catch (Exception e) {
            log.warn("Error at findAllCommentsWithPagination function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException("Not Found");
        }
        return response;
    };


    @Override
    public CommentDetail postComment(CommentReq req) throws BadResquestException{
        User user = iCurrentUser.getUser();
        CommentDetail response;
        Comments casted = new Comments();
        Comments saved;
        Blog currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(req.getTargetId(),SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Blog  found: {}", currentObj);
                BeanUtils.copyProperties(req, casted);
                casted.setTargetName(SharedConstance.BLOG.COMMENT_FOR_BLOG);
                casted.setUser(user);
                saved =  commentsRepository.save(casted);
                response = commentsRepository.findDetailById(saved.getId());
            } else {
                log.info("blog not found");
                throw new NotFoundException(MSG.get(NOT_FOUND_BLOG_BY_ID));
            }
        } catch (Exception e) {
            log.warn("Error at findDetailById function with message: {}", e.getMessage());
            throw new BadResquestException(MSG.get(NOT_FOUND_BLOG_BY_ID));
        }
        return response;
    }







}
