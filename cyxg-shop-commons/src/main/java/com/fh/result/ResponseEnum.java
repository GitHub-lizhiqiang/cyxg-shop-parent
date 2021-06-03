package com.fh.result;

public enum ResponseEnum {
    /* 说明常量的值 */
    TOKEN_INVALID(6006,"用户失效"),
    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    WRING_PASSWORD(6001,"密码错误"),
    PASSWORD_NOT_NULL(6002,"密码不能为空"),
    ACCOUNT_NOT_EXIST(6003,"账号不存在"),
    ACCOUNT_INVALID(6004,"账号无效"),
    IS_NULL(6005,"账号或密码不能为空"),
    PERMISSION_INVALID(6007,"用户没有该权限"),

    ;
    /* 私有化属性*/
    private Integer code;
    private  String msg;
    /*生成私有的（防止创建对象）构造函数*/
    private ResponseEnum(Integer code,String msg) {
        this.code=code;
        this.msg=msg;
    }
    /* 生成get方法*/
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
