package com.atguigu.commonutils;

/**
 * Date: 2022/6/21
 * Author:George
 * Description:
 */
public enum ResultCode {

    SUCCESS(20000),
    ERROR(20001);

    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
