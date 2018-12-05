package com.example.demo.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * Created by jcl on 2018/12/5
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        JSONObject resp = new JSONObject();
        resp.put("status", HttpStatus.BAD_REQUEST.value());
        resp.put("error", HttpStatus.BAD_REQUEST);
        resp.put("message", e.getMessage());
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(Exception e) {
        JSONObject resp = new JSONObject();
        resp.put("status", HttpStatus.NOT_FOUND.value());
        resp.put("error", HttpStatus.NOT_FOUND);
        resp.put("message", e.getMessage());
        return new ResponseEntity(resp, HttpStatus.NOT_FOUND);
    }


}
