package com.rkb.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-1 下午8:05
 */
@Alias("ModelAndCore")
public class ModelAndCore {
    private Long id;
    private String name;
    private String describe_;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe_() {
        return describe_;
    }

    public void setDescribe_(String describe_) {
        this.describe_ = describe_;
    }
}
