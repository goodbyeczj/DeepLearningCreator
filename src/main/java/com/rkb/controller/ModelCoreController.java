package com.rkb.controller;

import com.rkb.pojo.Model;
import com.rkb.pojo.ModelCore;
import com.rkb.pojo.PublicModel;
import com.rkb.service.ModelCoreService;
import com.rkb.transform.MapToGson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-17 下午4:31
 */
@Controller
@RequestMapping("project")
public class ModelCoreController {
    @Autowired
    ModelCoreService modelCoreService;
    @Autowired(required = false)
    HttpServletRequest request;
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model, ModelCore modelCore){
        Long userId = (Long) request.getSession().getAttribute("id");
        modelCore.setUserId(userId);
        model.setUserId(userId);
        modelCoreService.saveCore(modelCore,model);
        return model.getId().toString();
    }
    @RequestMapping("/update")
    @ResponseBody
    public String updateCore(ModelCore modelCore){
        Long userId = (Long) request.getSession().getAttribute("id");
        modelCore.setUserId(userId);
        modelCoreService.updateCore(modelCore);
        return "";
    }
    @RequestMapping("/copyCore")
    @ResponseBody
    public String copyCore(Model model, ModelCore modelCore,int publicId){
        Long userId = (Long) request.getSession().getAttribute("id");
        modelCore.setUserId(userId);
        model.setUserId(userId);
        PublicModel publicModel = modelCoreService.copyCore(model,modelCore,publicId);
        Long id = model.getId();
        return id.toString();
    }
    @RequestMapping("/showCore")
    @ResponseBody
    public String showCore(HashMap map){
        List<PublicModel> publicModel = modelCoreService.showCore();
        map.put("PublicModels",publicModel);
        return MapToGson.MapToGson(map);
    }
}
