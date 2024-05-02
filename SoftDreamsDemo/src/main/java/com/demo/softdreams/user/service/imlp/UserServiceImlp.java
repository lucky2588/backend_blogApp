package com.demo.softdreams.user.service.imlp;

import com.demo.softdreams.administrator.dto.other.CategoryDetail;
import com.demo.softdreams.config.LocalDateTimeConfig;
import com.demo.softdreams.config.ModelMapperConfig;
import com.demo.softdreams.core.dto.UserInfo;
import com.demo.softdreams.core.entites.Category;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.core.sercurity.iCurrentImpl;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.res.ChangeInfoUser;
import com.demo.softdreams.shared.res.ChangePassword;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.shared.respository.UserRepository;
import com.demo.softdreams.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.demo.softdreams.shared.common.ResponseConstance.*;
import static com.demo.softdreams.shared.common.ResponseConstance.NOT_FOUND_CATEGORY_BY_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImlp implements UserService {
    @Autowired
    private UserRepository dataSource;
    private final ModelMapperConfig mapperConfig;
    private final LocalDateTimeConfig localDateTimeConfig;
    private final iCurrentImpl iCurrentUser;
    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();




    @Override
    public UserInfo findUserById(Long id) throws NotFoundException {
        UserInfo response = new UserInfo();
        User currentObj;
        try {
            currentObj = dataSource.findByIdAndIsEnableTrueAndDeletedFalse(id);
            if (currentObj != null) {
                log.info("Category  found: {}", currentObj);
                response = mapperConfig.modelMapper().map(currentObj, UserInfo.class);
                log.info("Cast {} to {} before return front end successful", User.class.getSimpleName(), UserInfo.class.getSimpleName());
                log.info("Find User successful");
            } else {
                log.info("user not found");
                throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
//                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at findDetailById function with message: {}", e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }

    @Override
    public UserInfo changeInfoUser(ChangeInfoUser userInfo) throws NotFoundException{
        UserInfo response = new UserInfo();
        User currentObj;
        User casted = new User();
        User saved;
        User userGetToken = iCurrentUser.getUser();
        log.info("Current ID " +userGetToken.getId());
        log.info("Current id res " +userInfo.getId());
        try {
            currentObj = dataSource.findByIdAndIsEnableTrueAndDeletedFalse(userInfo.getId());
            if(userGetToken.getId() != currentObj.getId()){
                throw new BadResquestException("Not Vaild");
            }
            if (currentObj != null) {
                log.info("User found: {}", currentObj);
                BeanUtils.copyProperties(userInfo, currentObj); // mapping pros request for casted
                saved = dataSource.save(currentObj);
                response = mapperConfig.modelMapper().map(saved, UserInfo.class);
                log.info("Cast {} to {} before return front end successful", User.class.getSimpleName(), UserInfo.class.getSimpleName());
                log.info("Find User successful");
            } else {
                log.info("user not found");
                throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
//                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at  function with message: {}", e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }

    @Override
    public UserInfo changePassword(ChangePassword req) throws NotFoundException,BadResquestException {
        UserInfo response = new UserInfo();
        User currentObj;
        User saved;
        User userGetToken = iCurrentUser.getUser();
        try {
            currentObj = dataSource.findByIdAndIsEnableTrueAndDeletedFalse(req.getId());
            if(userGetToken.getId() != currentObj.getId()){
                throw new BadResquestException("Not Vaild");
            }
            if (currentObj != null) {
                log.info("User found: {}", currentObj);
                String passNew = encoder.encode(req.getPasswordNew());
                String passOld = encoder.encode(req.getPasswordOld());
                if(passNew.equals(req.getPasswordOld())){
                    throw  new BadResquestException("Password old not equal with Password new ");
                }
                if(!encoder.matches(req.getPasswordOld(), currentObj.getPassword())){
                    throw new BadResquestException(" Password old not vail  ... ");
                }
                currentObj.setPassword(encoder.encode(req.getPasswordNew()));
                saved = dataSource.save(currentObj);
                response = mapperConfig.modelMapper().map(saved, UserInfo.class);
                log.info("Cast {} to {} before return front end successful", User.class.getSimpleName(), UserInfo.class.getSimpleName());
                log.info("Change User Info successful");
            } else {
                log.info("user not found");
                throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
//                throw new RestControllerException(customApiResponse.getResponseStatus(), customApiResponse.getResponseCode(), Thread.currentThread().getStackTrace()[1].getMethodName(), customApiResponse.getResponseMessage(), "find Id Fail !! ", localDateTimeConfig.getLocalTime());
            }
        } catch (Exception e) {
            log.warn("Error at findDetailById function with message: {}", e.getMessage());
            throw new NotFoundException(MSG.get(NOT_FOUND_CATEGORY_BY_ID));
        }
        return response;
    }


}
