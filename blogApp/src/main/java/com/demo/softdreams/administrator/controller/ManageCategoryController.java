package com.demo.softdreams.administrator.controller;

import com.demo.softdreams.administrator.dto.other.CategoryDTO;
import com.demo.softdreams.administrator.dto.other.CategoryDetail;
import com.demo.softdreams.administrator.service.ManageCategoryService;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.core.exception.RestControllerException;
import com.demo.softdreams.shared.respone.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
@RequestMapping("api/manage/category")
public class ManageCategoryController {
    @Autowired
    private ManageCategoryService service;

    // get All
    @GetMapping("getAll")
    public List<CategoryDTO> getAll(){
        return service.getAll();
    }


    @PostMapping("save")
    public CategoryDetail save(@RequestBody CategoryDetail item) throws RestControllerException ,NotFoundException, BadResquestException{
        return service.saveCategory(item);
    }


    @GetMapping("{id}")
    public CategoryDetail getDetail(@PathVariable Long id) throws RestControllerException,NotFoundException {
        return service.findCategorybyID(id);
    }

    @DeleteMapping("delete/{id}")
    public CustomApiResponse removeCategory(@PathVariable Long id) throws RestControllerException,NotFoundException {
        return service.deleteCategory(id);
    }


}
