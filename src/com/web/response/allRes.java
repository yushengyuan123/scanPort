package com.web.response;

import com.google.gson.Gson;
import com.web.ipInfo.ipInfo;
import utils.jsonUtils;
import utils.responseResult;

import java.util.List;

public class allRes {
    private final String code;
    private final String message;
    private final ipInfo[] data;

    public allRes(String code, String message, ipInfo[] data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //List返回
    public static String resList(String code, String message, List<?> data) {
        responseResult result = new responseResult(code, message, data);
        Gson resJson = new Gson();
        return resJson.toJson(result);
    }
}
