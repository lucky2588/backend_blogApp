package com.demo.softdreams.core.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ArgumentNotValidException extends BaseException {

    private HashMap<String, String> detailsMessage;

    public ArgumentNotValidException(int httpStatus, int errorCode, String nameFunction, String errorMessage, String path, LocalDateTime errorDate, HashMap<String, String> detailsMessage) {
        super(httpStatus, errorCode, nameFunction, errorMessage, path, errorDate);
        this.detailsMessage = detailsMessage;
    }
}
