package com.demo.softdreams.global.service;

import com.demo.softdreams.core.entites.Image;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.exception.NotFoundException;
import com.demo.softdreams.shared.res.FileRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    public FileRes uploadFile(MultipartFile file);



    public Image readFile(Integer id);

    public void deleteFile(Integer id);


}
