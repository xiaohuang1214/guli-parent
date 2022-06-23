package com.atguigu.servicebase.config.exceptionhandler;

import com.atguigu.commonutils.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date: 2022/6/23
 * Author:George
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    //状态码
    private ResultCode code;
    //异常信息
    private String msg;
}
