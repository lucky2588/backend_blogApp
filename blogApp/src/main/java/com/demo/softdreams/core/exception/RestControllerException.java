package com.demo.softdreams.core.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class RestControllerException extends BaseException {

    public RestControllerException(int httpStatus, int errorCode, String nameFunction, String errorMessage, String path, LocalDateTime errorDate) {
        super(httpStatus, errorCode, nameFunction, errorMessage, path, errorDate);
    }


}
