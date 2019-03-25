package com.rkb.util;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;


/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-23 上午10:10
 */

public class LoggerUtil {
    public static void setLogFileName(String filename){
        FileAppender appender= (FileAppender) Logger.getRootLogger().getAppender("FILE");//获取FileAppender对象
        String path = PathUtil.getResources("")+"/"+filename+".log";
        appender.setFile(path);//重新设置输出的日志的路径和文件名,动态地修改这个文件
    }
}
