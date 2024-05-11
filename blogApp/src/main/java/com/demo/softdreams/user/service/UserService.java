package com.demo.softdreams.user.service;

import com.demo.softdreams.core.dto.UserInfo;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.shared.respone.ChangeInfoUser;
import com.demo.softdreams.shared.respone.ChangePassword;

public interface UserService {



    UserInfo findUserById(Long id) throws NotFoundException;

    UserInfo changeInfoUser(ChangeInfoUser infoUser) throws NotFoundException;

    UserInfo changePassword(ChangePassword password) throws NotFoundException, BadResquestException;


}
