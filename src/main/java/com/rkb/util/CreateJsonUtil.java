package com.rkb.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rkb.bean.net.Net;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-10-19 上午11:09
 */
@Component
public class CreateJsonUtil {
    String FIT="fit";
    String COMPILE="compile";
    String EVALUATE="evaluate";
    public String createJson(Net net){
//        List list =new ArrayList();
//        list.add(dense);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        Set<Object> set = net.CreateNet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()){
            Object value = iterator.next();
            String json1 = gson.toJson(value);
            JsonObject jsonObject1 = gson.fromJson(json1,JsonObject.class);
            String valueName = value.getClass().getSimpleName();
            if (valueName.toLowerCase().equals(FIT)||valueName.toLowerCase().equals(EVALUATE)||valueName.toLowerCase().equals(COMPILE))
                jsonObject.add(valueName.toLowerCase()+value.hashCode(),jsonObject1);
            else
                jsonObject.add(valueName+value.hashCode(),jsonObject1);
        }
//        JsonArray jsonArray = gson.fromJson(json1,JsonArray.class);
//        jsonObject.add("Dense",jsonArray);
        System.out.println(jsonObject);
        String json = jsonObject.toString();
        return json;
    }
}
