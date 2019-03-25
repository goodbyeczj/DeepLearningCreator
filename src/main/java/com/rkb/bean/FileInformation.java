package com.rkb.bean;

import java.util.Date;



public class FileInformation {
    private int id;
    private String filename;
    private String path;
    private String filetype;

    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FileInformation [id=" + id + ", filename=" + filename + ", path=" + path + ", filetype=" + filetype
                + ", time=" + time + "]";
    }

}
