package com.demo.softdreams.administrator.service.impl;

import com.demo.softdreams.administrator.dto.other.CategoryDTO;
import com.demo.softdreams.administrator.dto.other.CategoryDetail;
import com.demo.softdreams.administrator.service.ManageCategoryService;
import com.demo.softdreams.config.LocalDateTimeConfig;
import com.demo.softdreams.config.ModelMapperConfig;
import com.demo.softdreams.core.entites.Category;
import com.demo.softdreams.shared.common.ResponseConstance;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.exception.RestControllerException;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.shared.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.demo.softdreams.shared.common.ResponseConstance.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManageCategoryServiceImlp implements ManageCategoryService {
    @Autowired
    private CategoryRepository dataSource;
    private final ModelMapperConfig mapperConfig;
    private final LocalDateTimeConfig localDateTimeConfig;



    @Override
    public List<CategoryDTO> getAll() {
        return dataSource.getList().stream().map((item)->{
           CategoryDTO response;
         return  response = mapperConfig.modelMapper().map(item, CategoryDTO.class);
                }
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDetail saveCategory(CategoryDetail item) throws RestControllerException ,NotFoundException,BadResquestException{
        CustomApiResponse customApiResponse = new CustomApiResponse();
        CategoryDetail response = new CategoryDetail();
        Category casted = new Category(); // create obj emmty to mapping || binding data , proprites --> using for  as draft before save dataSource
        Category saved;  // using save database
        Category currentObj; // get Current Now -> using get Id , compare ....
        item.trim();
        log.info("item after trim {}" +item);
        log.info("Trim all string fields successful");
        // transform arraylist to string. ex: ["a", "b"] -> "a;b"
        try {
            if (item.getId() == null) {   // for Create
                BeanUtils.copyProperties(item, casted); // mapping pros request for casted
                log.info("Binding pros from Resquest to Casted Seccess !!  ");
                saved = dataSource.save(casted); // save to DB
                response = mapperConfig.modelMapper().map(saved, CategoryDetail.class);
                log.info("Cast from {} to {} successful before return to frontend when create", CategoryDetail.class.getSimpleName(), CategoryDetail.class.getSimpleName());
                response.setResponseStatus(HttpStatus.OK.value());
                response.setResponseCode(ResponseConstance.OVER_COUNT_OTP_CODE);
                response.setResponseMessage(ResponseConstance.SAVE_SUBSCRIPTION_SUCCESSFUL_MSG);
                log.info("Create new Category successful");
            } else { // for Update
                    currentObj = dataSource.findByIdAndDeleted(item.getId(),SharedConstance.DeleteInd.NOT_DELETE);
                    if(currentObj != null){ // find Id seccess
                        BeanUtils.copyProperties(item, casted); // mapping pros request for casted
                        saved = dataSource.save(casted);
                        response = mapperConfig.modelMapper().map(saved, CategoryDetail.class);
                        log.info("Cast from {} to {} successful before return to frontend when update", CategoryDetail.class.getSimpleName(), CategoryDetail.class.getSimpleName());
                        response.setResponseStatus(HttpStatus.OK.value());
                        response.setResponseCode(ResponseConstance.UPDATE_CATEGORY_SECCESS);
                        response.setResponseMessage(ResponseConstance.SAVE_CATEGORY_SUCCESSFUL_MSG);
                        log.info("Update Category successful");
                    }else {
                        log.info("Category not found with subId = {} when update", item.getId());
                        customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                        customApiResponse.setResponseCode(NOT_FOUND_CATEGORY_BY_ID);
                        customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
                        throw new BadResquestException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
//                        throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "create-notify", localDateTimeConfig.getLocalTime());
                    }
            }
        }catch(Exception e){
            log.warn("Error at saveCategory function with message: {}", e.getMessage());
            response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setResponseCode(EXCEPTION);
            response.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }

    @Override
    public CategoryDetail findCategorybyID(Long id) throws  RestControllerException,NotFoundException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        CategoryDetail response = new CategoryDetail();
        Category currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(id,SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Category  found: {}", currentObj);
                response = mapperConfig.modelMapper().map(currentObj, CategoryDetail.class);
                log.info("Cast {} to {} before return front end successful", Category.class.getSimpleName(), CategoryDetail.class.getSimpleName());
//                response.setResponseStatus(HttpStatus.OK.value());
//                response.setResponseCode(FIND_ALL_ACTION_SUCCESSFUL);
//                response.setResponseMessage(MSG.get(FIND_CATEGORY_SUCCESSFUL));
                log.info("Find category successful");
            } else {
                log.info("category not found");
                customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                customApiResponse.setResponseCode(NOT_FOUND_CATEGORY_BY_ID);
                customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_CATEGORY_BY_ID_MSG));
                throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
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
    public CustomApiResponse deleteCategory(Long id) throws RestControllerException,NotFoundException {
        CustomApiResponse customApiResponse = new CustomApiResponse();
        Category currentObj;
        try {
            currentObj = dataSource.findByIdAndDeleted(id,SharedConstance.DeleteInd.NOT_DELETE);
            if (currentObj != null) {
                log.info("Category  found: {}", currentObj);
                currentObj.setDeleted(true);
                Category category = dataSource.findById(id).get();
                category.setDeleted(true);
                dataSource.save(category);
                customApiResponse.setResponseStatus(HttpStatus.OK.value());
                customApiResponse.setResponseCode(FIND_ALL_ACTION_SUCCESSFUL);
                customApiResponse.setResponseMessage(MSG.get(FIND_CATEGORY_SUCCESSFUL));
                log.info("Find category successful");
            } else {
                log.info("category not found");
                customApiResponse.setResponseStatus(HttpStatus.BAD_REQUEST.value());
                customApiResponse.setResponseCode(NOT_FOUND_CATEGORY_BY_ID);
                customApiResponse.setResponseMessage(MSG.get(NOT_FOUND_CATEGORY_BY_ID_MSG));
                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at deleted  function with message: {}", e.getMessage());
            customApiResponse.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            customApiResponse.setResponseCode(EXCEPTION);
            customApiResponse.setResponseMessage(e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return customApiResponse;
    }



}
