package com.rkb.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadFileUtil {

    /*
     * 读取指定路径下的目录名
     */
    public List<String> getFileName(String path) {
        File file = new File(path);
        List<String> list = new ArrayList<>();
        File[] fileList = file.listFiles();
        for (File file1:fileList) {
            list.add(file1.getName());
        }
        return list;
        }
    }
