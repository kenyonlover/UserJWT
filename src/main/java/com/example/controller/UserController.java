package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.itf.annotation.UserLoginToken;
import com.example.service.UserService;
import com.example.token.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    //登录
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "username1"),
            @ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "password1")
    })
    @PostMapping("/login")
    public Object login(String username, String password) throws Exception {
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findUserByUsername(username);
        if(userForBase==null){
            throw new Exception("登录失败,用户不存在");
        }else {
            if (!userForBase.getPassword().equals(password)){
                throw new Exception("登录失败,用户密码错误");
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                jsonObject.put("message", "登录成功");
                jsonObject.put("status", 200);
                return jsonObject;
            }
        }
    }

    //注册
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "username"),
            @ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "password")
    })
    @PostMapping("/register")
    public Object register(String username, String password) throws Exception {
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findUserByUsername(username);
        if(userForBase!=null){
            throw new Exception("注册失败,用户已存在");
        }else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.addUser(user);
            jsonObject.put("user", user);
            jsonObject.put("message", "注册成功");
            jsonObject.put("status", 200);
            return jsonObject;
        }
    }

    //更改密码
    @UserLoginToken
    @ApiOperation("更改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", defaultValue = "1"),
            @ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "password")
    })
    @PostMapping("/update")
    public Object update(Integer id, String oldPassword, String password) throws Exception {
        JSONObject jsonObject=new JSONObject();
        User userForBase = userService.findUserById(id);
        if(userForBase==null){
            throw new Exception("失败,用户不存在");
        }else if(!userForBase.getPassword().equals(oldPassword)){
            throw new Exception("失败，原密码错误");
        }else {
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            userService.updatePwdById(user);
            jsonObject.put("message", "修改密码成功");
            jsonObject.put("status", 200);
            return jsonObject;
        }
    }

    @UserLoginToken
    @ApiOperation("获取消息，验证是否登录成功")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "身份令牌", required = false)
    })
    @GetMapping("/getMessage")
    public Object getMessage(){
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("message", "你已通过验证");
        retMap.put("status", 200);
        return retMap;
    }
}
