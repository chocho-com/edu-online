package com.chocho.eduservice.handler;

import com.chocho.common.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "全局异常")
@AllArgsConstructor
@NoArgsConstructor
public class ChoChoException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "消息")
    private String message;
}