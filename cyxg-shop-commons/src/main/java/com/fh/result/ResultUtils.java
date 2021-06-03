package com.fh.result;

public class ResultUtils {
    /*私有化属性*/
    private Integer code;
    private String msg;
    private Object data;

    /* 创建私有的（防止创建对象）构造函数*/
    private ResultUtils(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    /*多个构造函数 可以应付多种情况  参数问题*/
    private ResultUtils(Integer code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;

    }

    public static ResultUtils success(){
        return new ResultUtils(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg());
    }
    public static ResultUtils success(Object data){
        return new ResultUtils(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg(),data);
    }

    public static ResultUtils success(ResponseEnum responseEnum ){
        return new ResultUtils(responseEnum.getCode(),responseEnum.getMsg());
    }
    public static ResultUtils success(ResponseEnum responseEnum,Object data){
        return new ResultUtils(responseEnum.getCode(),responseEnum.getMsg(),data);
    }
    public static ResultUtils error(){
        return new ResultUtils(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMsg());
    }
    public static ResultUtils error(Object data){
        return new ResultUtils(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMsg(),data);
    }
    public static ResultUtils error(ResponseEnum responseEnum ){
        return new ResultUtils(responseEnum.getCode(),responseEnum.getMsg());
    }
    public static ResultUtils error(ResponseEnum responseEnum,Object data){
        return new ResultUtils(responseEnum.getCode(),responseEnum.getMsg(),data);
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}
