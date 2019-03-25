package com.rkb.transform;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-9 下午1:37
 */

public class MapToGson {
    static Gson gson = new Gson();
    public static final String MapToGson(Map map){
        return gson.toJson(map);
    }
    public static final String ObjToGson(Object object){
        return gson.toJson(object);
    }
}
