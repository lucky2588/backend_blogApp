package com.demo.softdreams.global.service;

import com.demo.softdreams.core.entites.Image;
import com.demo.softdreams.shared.respone.FileRes;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public FileRes uploadFile(MultipartFile file);



    public Image readFile(Integer id);

    public void deleteFile(Integer id);


}
