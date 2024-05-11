package com.demo.softdreams.global.service.Impl;


import com.demo.softdreams.core.entites.Image;
import com.demo.softdreams.core.sercurity.iCurrentImpl;
import com.demo.softdreams.global.service.FileService;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.shared.respone.FileRes;
import com.demo.softdreams.core.respository.ImageRepository;
import com.demo.softdreams.core.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final iCurrentImpl iCurrentUser;


    private final UserRepository userRepository;
    private final ImageRepository dataSource;



    public FileRes uploadFile(MultipartFile file) {
        validateFile(file);
        try {
            Image items = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .build();
            dataSource.save(items);
            return new FileRes("http://localhost:8080/api/files/"+items.getId());
        } catch (IOException e) {
            throw new RuntimeException("Có lỗi xảy ra trong quá trình upload ảnh ");
        }
    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // Tên file không được trống
        if (fileName == null || fileName.isEmpty()) {
            throw new BadResquestException("Tên file không hợp lệ");
        }

        // Type file có nằm trong ds cho phép hay không
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if (!checkFileExtension(fileExtension)) {
            throw new BadResquestException("Type file không hợp lệ");
        }

        // Kích thước size có trong phạm vi cho phép không
        double fileSize = (double) (file.getSize() / 1_048_576);
        if (fileSize > 2) {
            throw new BadResquestException("Size file không được vượt quá 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }
    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }

    public Image readFile(Integer id) {
        Image image = dataSource.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found image");
                });
        return image;
    }

    public void deleteFile(Integer id) {
        Image image = dataSource.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found image");
                });
        dataSource.delete(image);
    }




}

