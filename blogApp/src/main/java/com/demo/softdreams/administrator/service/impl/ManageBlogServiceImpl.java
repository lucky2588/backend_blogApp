package com.demo.softdreams.administrator.service.impl;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
import com.demo.softdreams.administrator.service.ManageBlogService;
import com.demo.softdreams.config.LocalDateTimeConfig;
import com.demo.softdreams.config.ModelMapperConfig;
import com.demo.softdreams.core.entites.Blog;
import com.demo.softdreams.core.entites.Category;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.core.sercurity.iCurrentImpl;
import com.demo.softdreams.shared.common.ResponseConstance;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.exception.RestControllerException;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.shared.res.PageData;
import com.demo.softdreams.shared.respository.BlogRepository;
import com.demo.softdreams.shared.respository.CategoryRepository;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.demo.softdreams.shared.common.ResponseConstance.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManageBlogServiceImpl implements ManageBlogService {
    private final CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository dataSource;
    @Autowired
    private BlogRepository BlogRepository;
    private final iCurrentImpl iCurrent;
    private final ModelMapperConfig mapperConfig;
    private final LocalDateTimeConfig localDateTimeConfig;


    @Override
    public PageData<BlogItems> findAllBlogsWithPagination(String textSearch, String active,List<Long> categories, String sortProperty, String sortOrder, int page, int pageSize) throws NotFoundException {
        Page<BlogItems> blogItems;
        PageData<BlogItems> response = new PageData<>();
        String softCache = "";
        Pageable pageable;
        Boolean isEmptyCategoris = false;
        try {
            if(categories == null || categories.isEmpty()){
                isEmptyCategoris = true;
            }
            pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(sortOrder), sortProperty));
            log.info(pageable.toString());
            blogItems = isEmptyCategoris == true ? dataSource.findAllBlogWithPaginationNotFilterCategory(textSearch.trim(),active, pageable) : dataSource.findAllBlogWithPagination(textSearch.trim(),active,categories, pageable);
            log.info("Find all blogs in module manage blog successful with size = {}", blogItems.getContent().size());
            response.setData(blogItems.getContent());
            response.setPageCurrent(blogItems.getPageable().getPageNumber()); // page  index now
            response.setTotalElements(blogItems.getTotalElements()); // total element
            response.setTotalPages(blogItems.getTotalPages()); // page Totals
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
    public CustomApiResponse changeActiveBlog(Long id, Integer active) throws RestControllerException, NotFoundException, BadResquestException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        BlogDetail response = new BlogDetail();
        Blog currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(id,SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Blog  found: {}", currentObj);
                currentObj.setActive(active == 1 ? SharedConstance.BLOG.PUBLIC : SharedConstance.BLOG.PRIVATE);
                dataSource.save(currentObj);
                response = mapperConfig.modelMapper().map(currentObj, BlogDetail.class);
                log.info("Cast {} to {} before return front end successful", Blog.class.getSimpleName(), BlogDetail.class.getSimpleName());
                response.setResponseStatus(HttpStatus.OK.value());
                response.setResponseCode(FIND_BLOG_SUCCESSFUL);
                response.setResponseMessage(MSG.get(FIND_BLOG_SUCCESSFUL));
                log.info("Find blog successful");
            } else {
                log.info("blog not found");
                customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                customApiResponse.setResponseCode(NOT_FOUND_BLOG_BY_ID);
                customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_BLOG_BY_ID));
                throw new NotFoundException(MSG.get(NOT_FOUND_BLOG_BY_ID));
//                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at findDetailById function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }

    @Override
    public List<BlogItems> findAllBlogsWithPaginationList(String textSearch, String active, List<Long> categories) {
        List<BlogItems> items = dataSource.findListBlogWithPagination(textSearch,active,categories);
        log.info("Log in Find All Panion " +items);
        return items;
    }

    @Override
    public BlogDetail saveBlog(SaveBlogReq req) throws RestControllerException, NotFoundException, BadResquestException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        BlogDetail response = new BlogDetail();
        Blog casted = new Blog(); // create obj emmty to mapping || binding data , proprites --> using for  as draft before save dataSource
        Blog saved;  // using save database
        Blog currentObj; // // get Current Now -> using get Id , compare ....
        Set<Category> categorySet;
        req.trim();
        log.info("item after trim {}" +req);
        log.info("Trim all string fields successful");
        // transform arraylist to string. ex: ["a", "b"] -> "a;b"
        try {
            if (req.getId() == null) {   // for Create
                log.info("Binding pros from Resquest to Casted Seccess !!  ");
                categorySet = categoryRepository.findAllByIdInAndDeleted(req.getCategoryList(),Boolean.FALSE);
                User user = iCurrent.getUser();
                casted.setUser(user);
                casted.setCategories(categorySet);
                casted.setCreatedDate(LocalDate.now());
                BeanUtils.copyProperties(req, casted); // mapping pros request for casted
                log.info("Value of Catsed " +casted);
                saved = dataSource.save(casted); // save to DB
                response = mapperConfig.modelMapper().map(saved, BlogDetail.class);
                log.info("Cast from {} to {} successful before return to frontend when create", BlogDetail.class.getSimpleName(), BlogDetail.class.getSimpleName());
                response.setResponseStatus(HttpStatus.OK.value());
                response.setResponseCode(ResponseConstance.SAVE_SECCESS);
                response.setResponseMessage(ResponseConstance.SAVE_BLOG_SUCCESSFUL_MSG);
                log.info("Create new Blog successful");
            } else { // for Update
                currentObj = dataSource.findByIdAndDeleted(req.getId(),SharedConstance.DeleteInd.NOT_DELETE);
                if(currentObj != null){ // find Id seccess
                    log.info("Binding pros from Resquest to Casted Seccess !!  ");
                    categorySet = categoryRepository.findAllByIdInAndDeleted(req.getCategoryList(),Boolean.FALSE);
                    BeanUtils.copyProperties(req, casted); // mapping
                    casted.setCategories(categorySet);
                    casted.setCreatedDate(currentObj.getCreatedDate());
                    saved = dataSource.save(casted);
                    response = mapperConfig.modelMapper().map(casted, BlogDetail.class);
//                    response.setCategories(req.getCategoryList());
                    log.info("Cast from {} to {} successful before return to frontend when update", BlogDetail.class.getSimpleName(), BlogDetail.class.getSimpleName());
                    response.setResponseStatus(HttpStatus.OK.value());
                    response.setResponseCode(ResponseConstance.UPDATE_BLOG_SECCESS);
                    response.setResponseMessage(UPDATE_TWO_FACTOR_SUCCESSFUL_MSG);
                    log.info("Update Blog successful");
                }else {
                    log.info("Blog not found with subId = {} when update", req.getId());
                    customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                    customApiResponse.setResponseCode(NOT_FOUND_BLOG_BY_ID);
                    customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_BLOG_BY_ID));
                    throw new BadResquestException(MSG.get(NOT_FOUND_BLOG_BY_ID));
//                        throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "create-notify", localDateTimeConfig.getLocalTime());
                }
            }
        }catch(Exception e){
            log.warn("Error at saveBlog function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }


    @Override
    public CustomApiResponse deleteBlog(Long id) throws   NotFoundException, BadResquestException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        Blog currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(id,SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Blog  found: {}", currentObj);
                currentObj.setDeleted(true);
                dataSource.save(currentObj);
                customApiResponse.setResponseStatus(HttpStatus.OK.value());
                customApiResponse.setResponseCode(DELETED_ACTION_SUCCESSFUL);
                customApiResponse.setResponseMessage(MSG.get(FIND_BLOG_SUCCESSFUL));
                log.info("Find Blog successful");
            } else {
                log.info("Blog not found");
                customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                customApiResponse.setResponseCode(NOT_FOUND_CATEGORY_BY_ID);
                customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_BLOG_BY_ID_MSG));
                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at deleted  function with message: {}", e.getMessage());
            customApiResponse.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            customApiResponse.setResponseCode(EXCEPTION);
            customApiResponse.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_BLOG_BY_ID_MSG));
        }
        return customApiResponse;
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
    
    
}
