package com.demo.softdreams.administrator.service;

import com.demo.softdreams.administrator.dto.other.CategoryDTO;
import com.demo.softdreams.administrator.dto.other.CategoryDetail;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.core.exception.RestControllerException;
import com.demo.softdreams.shared.respone.CustomApiResponse;

import java.util.List;

public interface ManageCategoryService {


    List<CategoryDTO> getAll();

    CategoryDetail saveCategory(CategoryDetail item) throws  RestControllerException ,NotFoundException, BadResquestException;

    CustomApiResponse deleteCategory(Long id) throws  RestControllerException,NotFoundException;

    CategoryDetail findCategorybyID(Long id) throws RestControllerException,NotFoundException;
}
