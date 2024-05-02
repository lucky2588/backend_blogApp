package com.demo.softdreams.shared.exception;

import com.demo.softdreams.config.LocalDateTimeConfig;
import com.demo.softdreams.shared.res.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static com.demo.softdreams.shared.common.ResponseConstance.FIELD_INVALID;
import static com.demo.softdreams.shared.common.ResponseConstance.MSG;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class HandleRestControllerException {

    private final LocalDateTimeConfig localDateTimeConfig;




//    @ExceptionHandler(BadResquestException.class)
//    public ResponseEntity<?> handlenBadresquestException(BadResquestException e) {
//        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
    // xử lí custom trả về cho exception Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlenNotFoundException(NotFoundException e){
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND,e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlenOtherException(Exception e){
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {
        log.warn("Error at {} function with message: {}", ex.getNameFunction(), ex.getError());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(ex);
    }


    @ExceptionHandler(RestControllerException.class)
    public ResponseEntity<RestControllerException> handleRestControllerException(RestControllerException ex) {
        log.warn("Error at {} function with message: {}", ex.getNameFunction(), ex.getError());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(ex);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ArgumentNotValidException> handleArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HashMap<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.warn("Error at {} function with message: {}", ex.getParameter().getExecutable().getName(), ex.getMessage());
        ArgumentNotValidException notValidException = new ArgumentNotValidException(HttpStatus.BAD_REQUEST.value(), FIELD_INVALID, ex.getParameter().getExecutable().getName(), MSG.get(FIELD_INVALID), request.getServletPath(), localDateTimeConfig.getLocalTime(), fieldErrors);
        return ResponseEntity.status(notValidException.getStatus()).body(notValidException);
    }


    @ExceptionHandler({BindException.class})
    public ResponseEntity<ArgumentNotValidException> handleArgumentNotValidException(BindException ex, HttpServletRequest request) {
        HashMap<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.warn("Error at api: {} with message: {}", request.getServletPath(), ex.getMessage());
        ArgumentNotValidException notValidException = new ArgumentNotValidException(HttpStatus.BAD_REQUEST.value(), FIELD_INVALID, ex.getNestedPath(), MSG.get(FIELD_INVALID), request.getServletPath(), localDateTimeConfig.getLocalTime(), fieldErrors);
        return ResponseEntity.status(notValidException.getStatus()).body(notValidException);
    }
//



}
