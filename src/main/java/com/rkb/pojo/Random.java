package com.rkb.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-11-14 下午7:25
 */
@Alias("Random")
public class Random {
    int id;
    String core;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }
}
