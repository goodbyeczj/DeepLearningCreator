package com.rkb.controller;

import com.rkb.bean.Train;
import com.rkb.util.ExcePythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-20 上午10:23
 */
@RequestMapping("/train")
@Controller
public class TrainCtl {
    @Autowired
    ExcePythonUtil excePythonUtil;
    @Autowired
    Train train;
    @RequestMapping("/loss")
    @ResponseBody
    public String trainLoss(){
        String data = train.getLOSS();
        System.out.println("loss :"+data);
//        train.setLOSS("");
        return data;
    }
    @RequestMapping("/acc")
    @ResponseBody
    public String trainAcc(){
        String acc = train.getACC();
        System.out.println("acc:"+acc);
//        train.setACC("");
        return acc;
    }
    @RequestMapping("/valLoss")
    @ResponseBody
    public String trainValLoss(){
        String valLoss = train.getVAL_LOSS();
//        train.setVAL_LOSS("");
        return valLoss;
    }
    @RequestMapping("/valAcc")
    @ResponseBody
    public String trainValAcc(){
        String valAcc = train.getVAL_ACC();
//        train.setVAL_LOSS("");
        return valAcc;
    }
}
