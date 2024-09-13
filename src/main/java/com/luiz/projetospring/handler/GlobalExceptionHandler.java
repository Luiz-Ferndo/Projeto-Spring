package com.luiz.projetospring.handler;

import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.lang.reflect.UndeclaredThrowableException;

@RestController
public class GlobalExceptionHandler {
    @Resource
    private MessageSource messageSource;
    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    private ResponseError responseError(String message, HttpStatus statusCode){
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.getError(message);
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handlerGeneral(Exception e, WebRequest request){
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException)){
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return HandlerExceptionResolver((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {
            String message = messageSource.getMessage("Error.server", new Object[]{e.getMessage(), null});
            ResponseError error = responseError(message, HttpStatus.CONFLICT);
            return HandleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    private ResponseEntity<Object> HandleExceptionInternal(Exception e, ResponseError error, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
    }
}

