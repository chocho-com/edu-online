package com.chocho.eduservice.controller;

import com.chocho.common.R;
import com.chocho.eduservice.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 不爱编程的程序猿
 * 2018/11/8
 */
@RestController
@RequestMapping
@Api(description = "登录模块")
//跨域问题
@CrossOrigin
public class LoginController {

    @PostMapping(value = "/login")
    @ApiOperation("登录")
    public R Login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    @ApiOperation("用户信息")
    public R info(){
        return R.ok().data("roles", "[admin]")
                     .data("name", "admin")
                     .data("avater", "image");
    }
}
