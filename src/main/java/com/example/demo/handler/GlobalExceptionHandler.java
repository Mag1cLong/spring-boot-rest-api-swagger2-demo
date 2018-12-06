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

    /**
     * 参数非法返回400
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 资源找不到返回404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * 构建响应实体
     *
     * @param msg
     * @param status
     * @return
     */
    private ResponseEntity buildResponse(String msg, HttpStatus status) {
        JSONObject body = new JSONObject();
        body.put("status", status.value());
        body.put("error", status);
        body.put("message", msg);
        return new ResponseEntity(body, status);
    }
}
