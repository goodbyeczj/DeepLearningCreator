package com.rkb.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PathCreateFile {


    public File createFile(String path) {
        // 指定路径如果没有则创建并添加
        File file = new File(path);
        //获取父目录
        File fileParent = file.getParentFile();
        //判断是否存在
        if (!fileParent.exists()&&!fileParent.isDirectory()) {
            //创建父目录文件
            fileParent.mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    public File createFilePath(String path) {
        // 指定路径如果没有则创建并添加
        File file = new File(path);
        if (!file.exists()&&!file.isDirectory()) {
            //创建父目录文件
            file.mkdirs();
        }
        return file;
    }

}
