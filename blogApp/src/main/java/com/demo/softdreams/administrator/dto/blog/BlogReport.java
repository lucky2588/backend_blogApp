package com.demo.softdreams.administrator.dto.blog;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


public interface BlogReport {


    String getTitle();

    Long getView();
    Long getComment();

}
