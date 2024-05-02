package com.demo.softdreams.administrator.dto.blog;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// for Panation
public interface BlogItems {
    Long getId();
    String getTitle();
     Long getView();
    String getContent();

    String getImage();



    String getActive();
    LocalDate getCreatedDate();
//    String username();
    //    Date getAnnounceDate();

    List<String> getListCategory();




}
