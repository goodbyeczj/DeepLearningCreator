package com.rkb.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-29 下午6:06
 */
@Alias("ModelCore")
@Component
public class ModelCore {
    Long id;
    Long userId;
    Long modelId;
    String nodeCore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getNodeCore() {
        return nodeCore;
    }

    public void setNodeCore(String nodeCore) {
        this.nodeCore = nodeCore;
    }
}
