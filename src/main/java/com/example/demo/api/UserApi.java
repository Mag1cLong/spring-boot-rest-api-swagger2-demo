package com.example.demo.api;

import com.example.demo.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcl on 2018/12/5
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("users")
public class UserApi {
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
    public List<User> userList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage
    ) {
        return userList;
    }

    @ApiOperation("用户详情")
    @GetMapping("{userId}")
    public User user(
            @PathVariable @ApiParam(value = "用户id", required = true) String userId
    ) {
        return userList.get(0);
    }


    @ApiOperation("新增用户")
    @PostMapping
    public User save(
            @RequestParam @ApiParam(value = "用户名", required = true) String userName,
            @RequestParam @ApiParam(value = "密码", required = true) String password,
            @RequestParam(required = false) @ApiParam(value = "邮箱") String email
    ) {
        if(password.length()<6)throw new IllegalArgumentException("密码不能少于6位");
        User user = new User();
        user.setUserId("3");
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
//
//    @ApiOperation("更新用户")
//    @ApiImplicitParam(name = "user", value = "单个用户信息", dataType = "User")
//    @PutMapping("update")
//    public boolean update(User user) {
//        return userList.remove(user) && userList.add(user);
//    }
//
//    @ApiOperation("批量删除")
//    @ApiImplicitParam(name = "users", value = "N个用户信息", dataType = "List<User>")
//    @DeleteMapping("delete")
//    public boolean delete(@RequestBody List<User> users) {
//        return userList.removeAll(users);
//    }


}
