package com.boluo.groundedsegmentanythingbackend.enmus;

/**
 * @author kirito
 * @version 1.0
 * @description: TODO
 * @date 2023/6/10 14:28
 */
public class AppHttpCodeEnum {
    final Integer code;
    final String msg;

    AppHttpCodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
