package com.demo.softdreams.shared.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    private int status = HttpStatus.OK.value();
    private int code = ResStatus.SUCCESS;
    private String error;
    private Object body;
}
