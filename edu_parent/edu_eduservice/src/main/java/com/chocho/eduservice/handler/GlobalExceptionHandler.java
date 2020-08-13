package com.chocho.eduservice.handler;

import com.chocho.common.R;
import com.chocho.common.ResultCode;
import com.chocho.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
//aop切面（通知）
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //异常处理范围
    @ExceptionHandler(Exception.class)
    //响应页面
    @ResponseBody
    public R error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("服务器发生错误");
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        log.error(ExceptionUtil.getMessage(e));
        return R.error().code(ResultCode.BAD_SQL_GRAMMAR).message("sql错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(JsonParseException e){
        log.error(ExceptionUtil.getMessage(e));
        return R.error().code(ResultCode.JSON_PARSE_ERROR).message("json解析异常");
    }

    @ExceptionHandler(ChoChoException.class)
    @ResponseBody
    public R error(ChoChoException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}