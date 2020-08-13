package com.chocho.common;

public interface ResultCode {
    //成功
    int SUCCESS = 20000;
    //失败
    int ERROR = 201;
    //没有授权
    int AUTH = 300;
    //sql错误
    int BAD_SQL_GRAMMAR = 21001;
    //json解析异常
    int JSON_PARSE_ERROR = 21002;
    //参数不正确
    int PARAM_ERROR = 21002;
}
