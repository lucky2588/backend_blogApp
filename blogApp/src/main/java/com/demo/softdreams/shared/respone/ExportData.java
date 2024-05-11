package com.demo.softdreams.shared.respone;

import com.demo.softdreams.administrator.dto.blog.BlogReport;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExportData implements Serializable{

    public String typeReport;
    public String typeRole;

}
