package com.rkb.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-3-8 下午3:50
 */
@Alias("ModelData")
public class ModelData {
    Long id;
    Long modelData;
    String name;
    String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelData() {
        return modelData;
    }

    public void setModelData(Long modelData) {
        this.modelData = modelData;
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
