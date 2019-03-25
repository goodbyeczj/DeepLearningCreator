package com.rkb.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

@Component
public class FileUtil {
    /**
     * 使用BufferedWriter类写文本文件
     */
    @Autowired
    PathCreateFile pathCreateFile;
    public void write(String str, String fileName) {
        FileWriter output = null;
        BufferedWriter writer = null;
        try{
            pathCreateFile.createFile(fileName);
            output = new FileWriter(fileName);
            writer = new BufferedWriter(output);
            writer.write(str);
            System.out.println("写入成功");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != writer){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(null != output){
                try {
                    output.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


        /**
         * 读取到字节数组2
         *
         * @param filePath
         * @return
         * @throws IOException
         */
        public static byte[] toByteArray2(String filePath) throws IOException {

            File f = new File(filePath);
            if (!f.exists()) {
                throw new FileNotFoundException(filePath);
            }

            FileChannel channel = null;
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(f);
                channel = fs.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
                while ((channel.read(byteBuffer)) > 0) {
                    // do nothing
                    // System.out.println("reading");
                }
                return byteBuffer.array();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    /**


     * zip解压


     * @param srcFile        zip源文件


     * @param destDirPath     解压后的目标文件夹


     * @throws RuntimeException 解压失败会抛出运行时异常


     */


    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {


        long start = System.currentTimeMillis();


        // 判断源文件是否存在


        if (!srcFile.exists()) {


            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");


        }


        // 开始解压


        ZipFile zipFile = null;


        try {


            zipFile = new ZipFile(srcFile);


            Enumeration<?> entries = zipFile.getEntries();


            while (entries.hasMoreElements()) {


                ZipEntry entry = (ZipEntry) entries.nextElement();


//                System.out.println("解压" + entry.getName());


                // 如果是文件夹，就创建个文件夹


                if (entry.isDirectory()) {


                    String dirPath = destDirPath + "/" + entry.getName();


                    File dir = new File(dirPath);


                    dir.mkdirs();

                } else {


                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去


                    File targetFile = new File(destDirPath + "/" + entry.getName());


                    // 保证这个文件的父文件夹必须要存在


                    if(!targetFile.getParentFile().exists()){


                        targetFile.getParentFile().mkdirs();


                    }


                    targetFile.createNewFile();

                    // 将压缩文件内容写入到这个文件中


                    InputStream is = zipFile.getInputStream(entry);


                    FileOutputStream fos = new FileOutputStream(targetFile);


                    int len;


                    byte[] buf = new byte[BUFFER_SIZE];


                    while ((len = is.read(buf)) != -1) {


                        fos.write(buf, 0, len);


                    }


                    // 关流顺序，先打开的后关闭


                    fos.close();


                    is.close();


                }


            }


            long end = System.currentTimeMillis();

            System.out.println("解压完成，耗时：" + (end - start) +" ms");


        } catch (Exception e) {


            throw new RuntimeException("unzip error from ZipUtils", e);


        } finally {


            if(zipFile != null){


                try {


                    zipFile.close();


                } catch (IOException e) {

                    e.printStackTrace();


                }


            }

        }


    }
    }
