package com.rkb.pojo;

import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Size;
@Alias("User")
public class User {
    private long id;
    @Size(min = 2,max = 20)
    private String name;
    @Size(min = 6,max = 16)
    private String password;
    private String core;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }
}
