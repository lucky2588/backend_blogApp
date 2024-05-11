package com.demo.softdreams.shared.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonHttpRes {
    private boolean status = HttpStatus.MULTI_STATUS.is2xxSuccessful();
    private int code = 200;
    private String error;
    private Object body;
}
