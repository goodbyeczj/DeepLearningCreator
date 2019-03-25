package com.rkb.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-13 下午6:05
 */
@Alias("PublicDataSets")
public class PublicDataSets {
   int id;
   String name;
   String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
