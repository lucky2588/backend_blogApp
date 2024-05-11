package com.demo.softdreams.administrator.controller;
import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
import com.demo.softdreams.administrator.service.ManageBlogService;
import com.demo.softdreams.config.utilities.LocalDateTimeConfig;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.core.exception.RestControllerException;
import com.demo.softdreams.shared.respone.CustomApiResponse;
import static com.demo.softdreams.shared.common.ResponseConstance.*;

import com.demo.softdreams.shared.respone.IdReq;
import com.demo.softdreams.shared.respone.PageData;
import com.demo.softdreams.core.respository.BlogRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/manage/blog")
public class ManageBlogController  {
    private final BlogRepository blogRepository;
    private final LocalDateTimeConfig localDateTimeConfig;
    @Autowired
    private ManageBlogService service;


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





//    @ApiOperation(value = "Create or update a Blog")
    @PostMapping(value = "/save")
    public BlogDetail createOrUpdateBlog(@Valid @RequestBody SaveBlogReq req) throws  RestControllerException, NotFoundException, BadResquestException {
        BlogDetail response = service.saveBlog(req);
        if (response.getResponseCode() == INTERNAL ) {
            throw new RestControllerException(response.getResponseStatus(), response.getResponseCode(), "createOrUpdateBlog", response.getResponseMessage(), "/api/manage/Blog/save", localDateTimeConfig.getLocalTime());
        }
        return response;
    }

//    @ApiOperation(value = "Change active of Blog")
//    @PreAuthorize("hasAnyAuthority('" + SharedConstance.PermissionsCode.Admin.MANAGE_Blog_CHANGE_STATUS + "')")
//    @PostMapping(value = "/change-active/{active}")
//    public CustomApiResponse changeActiveBlog(@Valid @RequestBody IdReq req , @PathVariable Integer active) throws RestControllerException , NotFoundException, BadResquestException{
//        CustomApiResponse response = service.changeActiveBlog(req.getId(), active);
//        return response;
//    }

//    @ApiOperation(value = "Delete Blog")
    @DeleteMapping(value = "/delete/{id}")
//    @PreAuthorize("hasAnyAuthority('" + SharedConstance.PermissionsCode.Admin.MANAGE_Blog_DELETE + "')")
    public CustomApiResponse deleteSub(@PathVariable Long id) throws  RestControllerException, NotFoundException, BadResquestException {
        CustomApiResponse response = service.deleteBlog(id);
        if (response.getResponseCode() == INTERNAL || response.getResponseCode() == EXCEPTION) {
            throw new RestControllerException(response.getResponseStatus(), response.getResponseCode(), "deleteBlog", response.getResponseMessage(), "/api/manage/Blog/delete/" + id.toString(), localDateTimeConfig.getLocalTime());
        }
        return response;
    }


    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAnyAuthority('" + SharedConstance.PermissionsCode.Admin.MANAGE_Blog_VIEW + "')")
    public BlogDetail findBlogById(@PathVariable Long id) throws  RestControllerException, NotFoundException, BadResquestException {
        BlogDetail response = service.findBlogById(id);
        if (response.getResponseCode() == INTERNAL || response.getResponseCode() == EXCEPTION) {
            throw new RestControllerException(response.getResponseStatus(), response.getResponseCode(), "findBlogById", response.getResponseMessage(), "/api/manage/Blog/" + id.toString(), localDateTimeConfig.getLocalTime());
        }
        return response;
    }





//    @GetMapping("/report/export")
//    public ResponseEntity<byte[]> generatePdf() throws IOException, JRException {
//        byte[] pdfBytes = service.exportExport();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("filename", "report.pdf");
//        headers.setContentLength(pdfBytes.length);
//        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//    }

    @GetMapping("/report/export")
    public void sendReport(){
         service.exportData();
    }















}

