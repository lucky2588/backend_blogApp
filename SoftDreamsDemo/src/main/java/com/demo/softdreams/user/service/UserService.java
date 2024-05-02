package com.demo.softdreams.user.service;

import com.demo.softdreams.core.dto.UserInfo;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.res.ChangeInfoUser;
import com.demo.softdreams.shared.res.ChangePassword;
import com.demo.softdreams.shared.res.CustomApiResponse;
import org.aspectj.weaver.ast.Not;

public interface UserService {



    UserInfo findUserById(Long id) throws NotFoundException;

    UserInfo changeInfoUser(ChangeInfoUser infoUser) throws NotFoundException;

    UserInfo changePassword(ChangePassword password) throws NotFoundException, BadResquestException;


}
