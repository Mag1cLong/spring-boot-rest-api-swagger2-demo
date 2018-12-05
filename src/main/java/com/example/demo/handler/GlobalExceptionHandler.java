package com.example.demo.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jcl on 2018/12/5
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleException(Exception e) {
        JSONObject resp = new JSONObject();
        resp.put("status",HttpStatus.BAD_REQUEST.value());
        resp.put("msg",e.getMessage());
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }

}
