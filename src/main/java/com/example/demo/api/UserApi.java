package com.example.demo.api;

import com.example.demo.config.GlobalConfig;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.vo.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcl on 2018/12/5
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("users")
public class UserApi {
    @Resource
    private HttpServletResponse response;
    private final static List<User> userList = new ArrayList<>();

    {
        User user = new User();
        user.setUserId("1");
        user.setUserName("tom");
        user.setPassword("123456");
        userList.add(user);
        user = new User();
        user.setUserId("2");
        user.setUserName("jerry");
        user.setPassword("654321");
        userList.add(user);
    }



    @ApiOperation("用户列表")
    @GetMapping
    public List<User> getUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage
    ) {
        int from = (page - 1) * perPage > userList.size() ? userList.size() : (page - 1) * perPage;
        int to = (page * perPage) > userList.size() ? userList.size() : page * perPage;
        response.addHeader(GlobalConfig.HEADERS_X_TOTAL_COUNT,String.valueOf(userList.size()));
        return userList.subList(from, to);
    }

    @ApiOperation("用户详情")
    @GetMapping("{userId}")
    public User getUser(
            @PathVariable @ApiParam(value = "用户id", required = true) String userId
    ) throws Exception {
        User user = new User();
        user.setUserId(userId);
        int index = userList.indexOf(user);
        if (index < 0) throw new ResourceNotFoundException("用户不存在");
        return userList.get(index);
    }


    @ApiOperation("新增用户")
    @ApiResponses({
            @ApiResponse(code = 400, message = "密码不能少于6位"),
    })
    @PostMapping
    public User addUser(
            @RequestParam @ApiParam(value = "用户名", required = true) String userName,
            @RequestParam @ApiParam(value = "密码", required = true) String password,
            @RequestParam(required = false) @ApiParam(value = "邮箱") String email
    ) {
        if (password.length() < 6) throw new IllegalArgumentException("密码不能少于6位");
        User user = new User();
        Integer size = userList.size() + 1;
        user.setUserId(size.toString());
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        userList.add(user);
        return user;
    }

    @ApiOperation("修改用户")
    @PutMapping("{userId}")
    public User updateUser(
            @PathVariable @ApiParam(value = "用户id", required = true) String userId,
            @RequestParam(required = false) @ApiParam(value = "用户名") String userName,
            @RequestParam(required = false) @ApiParam(value = "密码") String password,
            @RequestParam(required = false) @ApiParam(value = "邮箱") String email
    ) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        userList.remove(user);
        userList.add(user);
        return user;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("{userId}")
    public void deleteUser(
            @PathVariable @ApiParam(value = "用户id", required = true) String userId
    ) {
        User user = new User();
        user.setUserId(userId);
        userList.remove(user);
    }


}
