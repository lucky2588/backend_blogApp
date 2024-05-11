
package com.demo.softdreams.administrator.service;

import com.demo.softdreams.administrator.dto.blog.BlogDetail;
import com.demo.softdreams.administrator.dto.blog.BlogItems;
import com.demo.softdreams.administrator.dto.blog.ChangeActive;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public interface AdminService {


    public void changeActiveBlog(ChangeActive res) ;




}
