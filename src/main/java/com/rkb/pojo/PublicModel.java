package com.rkb.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-13 下午6:07
 */
@Alias("PublicModel")
@Component
public class PublicModel {
    int id;
    String name;
    String decribe_;
    String core;

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

    public String getDecribe_() {
        return decribe_;
    }

    public void setDecribe_(String decribe_) {
        this.decribe_ = decribe_;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }
}
