package com.demo.softdreams.global.entites.dto;

import java.time.LocalDateTime;

public interface CommentDetail{

    Long getId();

    String getContent();
    LocalDateTime getCreatedDate();

    String getUsername();
    String getAvatar();



}