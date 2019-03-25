package com.rkb.util;

import com.rkb.bean.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-18 下午3:53
 */
@Component
public class ExcePythonUtil{
    @Autowired
    volatile Train train;
    private final static Logger logger = LoggerFactory.getLogger(ExcePythonUtil.class);
    Long userId;
    String PATH = PathUtil.getText();
    String REX = "\\[(.+)\\]";
    Process process;
    volatile Boolean flag;
    String TRAINED = "trained";
    String TRAIN = "train";
//    String PATH = "/home/lailai/Testing";

    public ExcePythonUtil(Long userId) {
        this.userId = userId;
    }
    public ExcePythonUtil(){}
    public int excePython(Long id) throws IOException, InterruptedException {
        String exe = "/home/lailai/anaconda3/bin/python3";
//        String exe = "/usr/bin/python3.5";
        String command = PATH+"/"+id.toString()+"/run.py";
        System.out.println(command);
        // 初始化日志路径
        String[] cmdArr = new String[] {exe, command};
        try {
            process = Runtime.getRuntime().exec(cmdArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        InputStream is = process.getInputStream();
//        DataInputStream dis = new DataInputStream(is);
//        String str = dis.readLine();
        Thread thread = new Thread(){
            @Override
            public void run() {
                InputStream is2 = process.getErrorStream();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                //        StringBuilder buf = new StringBuilder(); // 保存输出结果流
                String line = null;
                Pattern r = Pattern.compile(REX);
                // 现在创建 matcher 对象
                try {
                    while((line=br2.readLine())!=null) {
                        logger.debug(line);
                        logger.debug(String.valueOf(line.split(",").length));
                        Matcher m = r.matcher(line);
                        if (line.contains("loss")){
                            if (m.find()) {
                                line = m.group(1);
                                train.setLOSS(line);
                            }
                        }
                        else if (line.contains("acc")){
                            if (m.find()) {
                                line = m.group(1);
                                train.setACC(line);
                            }
                        }
                        else if (line.contains("val_l")){
                            if (m.find()) {
                                line = m.group(1);
                                train.setVAL_LOSS(line);
                            }
                        }
                        else if (line.contains("val_a")){
                            if (m.find()) {
                                line = m.group(1);
                                train.setVAL_ACC(line);
                            }
                        }
//                Thread.sleep(100);
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                finally {
                    try {
                        br2.close();
                        is2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        Thread thread1 = new Thread() {
            @Override
            public void run()
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;

                try
                {
                    while((line = in.readLine()) != null)
                    {
                        System.out.println("output: " + line);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
//        thread.join();
        try {
            Integer re = process.waitFor();
            System.out.println("train:"+re);
            return re;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("训练的stopTrain");
        stopTraining();
//            return "trained";
//        System.out.println(str);
        return 1;
    }

    public Long getUserId() {
        return userId;
    }
    // //jdk9以上
//    public Integer stopTrain(){
//        List<Long> pids = getPids(process);
//        for (Long pid:pids) {
//            try {
//                int t = Runtime.getRuntime().exec("kill 9"+pid).waitFor();
//                System.out.println("杀死进程"+pid);
//                System.out.println(pid);
//                System.out.println(t);
//                return t;
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
    public void stopTraining(){
        logger.debug("进入stopTrain方法");
        Integer pid = getPid(process);
        int t = 1;
        try {
            t = Runtime.getRuntime().exec("kill -9 "+pid).waitFor();
            System.out.println("杀死进程 :"+pid);
            System.out.println("stop:"+t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //JDK9以上
//    private List<Long> getPids(Process process) {
////获取PID
//        List<Long> pids = new ArrayList<>();
//        Stream<ProcessHandle> stream = process.descendants();
//        List<ProcessHandle> list = stream.collect(Collectors.toList());
//        for (ProcessHandle processHandle:
//                list) {
//            pids.add(processHandle.pid());
//        }
//        pids.add(process.pid());
//        return  pids;
//    }
    //JDK8
    private Integer getPid(Process process){
        Integer pid = -1;
        Field field = null;
        try {
            Class<?> clazz = Class.forName("java.lang.UNIXProcess");
            field = clazz.getDeclaredField("pid");
            field.setAccessible(true);
            pid = (Integer) field.get(process);
            return pid;
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return pid;
    }
//    public Long execute(){
//        Thread thread = new Thread(new Worker());
//        thread.start();
//        return thread.getId();
//
//
//    }
//    private class Worker implements Runnable{
//
//        @Override
//        public void run() {
//            try {
//                excePython(getUserId());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
