package com.demo.softdreams.core.exception;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonIgnoreProperties(value = {"cause", "localizedMessage", "message", "nameFunction", "stackTrace", "suppressed"})
public class BaseException extends Exception {

    private int status;
    private int code;
    private String nameFunction;
    private String error;
    private String path;
    @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
    private LocalDateTime date;

    public BaseException() {

    }

    public BaseException(int status, int code, String nameFunction, String error, String path, LocalDateTime date) {
        super();
        this.status = status;
        this.code = code;
        this.nameFunction = nameFunction;
        this.error = error == null ? getError() : error;
        this.path = path;
        this.date = date;
    }

}