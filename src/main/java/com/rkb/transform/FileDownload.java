package com.rkb.transform;

import com.rkb.util.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


/**
 * 下载文件
 * @version
 */
public class FileDownload {

    /**
     * @param response
     * @param filePath		//文件完整路径(包括文件名和扩展名)
     * @param fileName		//下载后看到的文件名
     * @return  文件名
     */
    public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception{

        byte[] data = FileUtil.toByteArray2(filePath);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset();
        //保证文件都是下载的，不是解析
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        //缓冲流
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        //刷新缓存，不懂就理解为2胖子一起出门给堵住了，你一脚踹过去让他们出去
        outputStream.flush();
        //保持好习惯，用完就关掉
        outputStream.close();
        response.flushBuffer();

    }

}