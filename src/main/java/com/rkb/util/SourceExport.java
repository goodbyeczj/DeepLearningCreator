package com.rkb.util;

import java.io.*;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-2-28 上午11:24
 */

public class SourceExport {
    static long i = 0 ;
    public static void main(String[] args) throws IOException {
        File dir = new File("/home/lailai/IdeaProjects/AIer-master/src");
        File target = new File("/home/lailai/text/dst.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(target));
        StringBuffer sb = new StringBuffer();
        loopRead(dir, sb);
        write(sb.toString(), bw);
    }
    private static void loopRead(File dir, StringBuffer sb) throws IOException {
        File[] files = dir.listFiles();
        if (files!=null)
            for(File file:files){
                if(file.isDirectory()){
                    loopRead(file, sb);
                }else {
                    if(file.length()!=0){
                        sb.append(readFileToString(file));
                    }
                }
            }
    }
    private static String readFileToString(File file) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = br.readLine())!=null){
            i++;
            String s = line.trim();
            if (s.length()==0) {
                continue;
            }
            if (s.startsWith("/") || s.startsWith("*")) {
                continue;
            }
            sb.append(line).append("\n");
        }
        System.out.println(i);
        return sb.toString();
    }
    private static void write(String str, Writer writer) throws IOException {
        writer.write(str);
    }
}
