package com.demo.softdreams.shared.exception;

import lombok.Data;

@Data
public class BizException extends BaseException{

    public BizException(int code,String msg){
        this.setStatus(code);
        this.setError(msg);
    }

    public BizException(int status,int code,String msg){
        this.setStatus(status);
        this.setCode(code);
        this.setError(msg);
    }
}
