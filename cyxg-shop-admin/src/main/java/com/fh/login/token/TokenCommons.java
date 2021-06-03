package com.fh.login.token;

import javax.servlet.http.HttpServletRequest;

public class TokenCommons {
    public static String token_pre="ACCESS_TOKEN";
    public static Long exp_time=30l;
    public static String exp_UserRedis="USER_CACHE";

    public static String setTokenKey(String username,String token,String typeName){
        return token_pre+":"+username+":"+typeName+":"+token;
    }
    public static String redisUser(String username,String type){
        return exp_UserRedis+":"+username+":"+type;
    }
    public static String redisTokenKey(String username,String token,String typeName){
        return token_pre+":"+username+":"+typeName+":"+token;
    }
    public static String typeTokenKey(String username,String typeName){
        return token_pre+":"+username+":"+typeName+":*";
    }
    public static String getDeviceType(HttpServletRequest request){
        String type = request.getHeader("User-Agent");
        String typeName="";

        if (type.contains("Android")||type.contains("Linux")) {
            System.out.println("Android移动客户端");
            typeName="Android";
        } else if (type.contains("iPhone")) {
            System.out   .println("iPhone移动客户端");
            typeName="iPhone";

        } else if (type.contains("iPad")) {
            System.out.println("iPad客户端");
            typeName="iPad";
        } else if(type.contains("Windows")){
            System.out.println("Windows");
            typeName="Windows";
        }
        return typeName;

    }
}
