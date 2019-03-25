package com.rkb.controller;

import com.rkb.pojo.DataSet;
import com.rkb.pojo.ModelData;
import com.rkb.pojo.PublicDataSets;
import com.rkb.service.DataSetService;
import com.rkb.transform.MapToGson;
import com.rkb.util.FileUtil;
import com.rkb.util.PathUtil;
import com.rkb.util.ReadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("data")
public class DataSetController {
    @Autowired
    private DataSetService dataSetService;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private ReadFileUtil readFileUtil;
    @Autowired
    FileUtil fileUtil;
    //上传数据
    @RequestMapping("/add")
    @ResponseBody
    public String addData(@RequestParam("file") MultipartFile file,DataSet dataSet) throws IOException {
        Long id = (Long) request.getSession().getAttribute("id");
        dataSet.setUserId(id);
        String fileName = file.getOriginalFilename();
        String realName = fileName.split("\\.")[0];
        dataSet.setName(realName);
        List<DataSet> list = dataSetService.findDataSetByUserIdAndName(dataSet);
        if (list.size()==0){
            dataSetService.upload(file, dataSet);
            dataSetService.compress(file,id);
            return MapToGson.ObjToGson(dataSet);
        }
        else {
//            throw new RuntimeException();
            return "error";
        }
//        modelAndView.setViewName("redirect:manage.do");
//        return modelAndView;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteModel(DataSet dataSet) {
        Long id = (Long) request.getSession().getAttribute("id");
        dataSetService.deleteData(dataSet, id);
        return "";
    }
    @RequestMapping("/showPublicDataSets")
    @ResponseBody
    public String showPublicDataSets(HashMap map) {
        List<PublicDataSets> publicDataSetsList = dataSetService.findPublicDataSets();
        map.put("publicDataSetsList",publicDataSetsList);
        return MapToGson.MapToGson(map);
    }
    @RequestMapping("/copyPublicDataSets")
    @ResponseBody
    public String copyPublicDataSets(HashMap map,DataSet dataSet,int publicId) {
        Long id = (Long) request.getSession().getAttribute("id");
        PublicDataSets publicDataSets= dataSetService.findPublicDataSetsById(dataSet,publicId,id);
//        map.put("dataSets",dataSet);
        return dataSet.getId().toString();
    }
    @RequestMapping("/findDataSetByUserId")
    @ResponseBody
    public String findDataSetByUserId(HashMap map){
        Long userId = (Long) request.getSession().getAttribute("id");
        List<DataSet> dataSets = dataSetService.findDataSetByUserId(userId);
//        System.out.println(dataSets.toString());
        map.put("dataSets",dataSets);
        return MapToGson.MapToGson(map);
    }
    @RequestMapping("/manage")
    public ModelAndView findModelByName(ModelAndView modelAndView) {
        Long id = (Long) request.getSession().getAttribute("id");
        List<DataSet> dataSetNameList = dataSetService.findDataSetByUserId(id);
        modelAndView.addObject("dataSetNameList", dataSetNameList);
        modelAndView.setViewName("dataManager");
        return modelAndView;
    }

    @RequestMapping("/ct_data")
    public void showCtData(PrintWriter out, String id) {
        String path = Thread.currentThread().getContextClassLoader().getResource("ct_data/picture").getPath();
        System.out.println(path);
        List<String> list = dataSetService.getFileNames(path);
        for (String i : list) {
            if (id.equals(i)) {
                List<String> list1 = dataSetService.getFileNames(path + i);
                StringBuffer sb = new StringBuffer();
                for (String j : list1) {
                    sb.append(j + ",");
                }
                out.print(sb.toString());
                break;
            }
        }
        out.flush();
        out.close();
    }

    @RequestMapping("/py_data")
    public void showPyData(PrintWriter out, String str) {
        String path = Thread.currentThread().getContextClassLoader().getResource("picture/classic_net").getPath();
        System.out.println(path);
        String core = fileUtil.readToString(path + str+".py");
        out.print(core);
        out.close();
    }
    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView) {
        fileUtil.write("234","/home/lailai/Test/file.txt");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping("/update_img")
    public ModelAndView updateImg(ModelAndView modelAndView) {
        String path = PathUtil.getText()+"/train_epoch_loss.jpg";
        try {
            Image image = ImageIO.read(new File(path));
            modelAndView.addObject("img",image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
    @RequestMapping("/addModelData")
    @ResponseBody
    public String addModelData(ModelData modelData, HashMap map){
        dataSetService.addModelData(modelData);
        map.put("msg","success");
        return MapToGson.MapToGson(map);
    }
    @RequestMapping("/showModelData")
    @ResponseBody
    public String showModelData(ModelData modelData, HashMap map){
        dataSetService.addModelData(modelData);
        map.put("msg","success");
        return MapToGson.MapToGson(map);
    }
}
