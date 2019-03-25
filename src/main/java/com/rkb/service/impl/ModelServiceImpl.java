package com.rkb.service.impl;

import com.rkb.bean.net.impl.fun.GooLeNet;
import com.rkb.bean.net.impl.seq.VggNet;
import com.rkb.dao.DataSetDao;
import com.rkb.dao.ModelCoreDao;
import com.rkb.dao.ModelDao;
import com.rkb.layers.DataSet;
import com.rkb.layers.Functional;
import com.rkb.layers.Sequential;
import com.rkb.pojo.Model;
import com.rkb.pojo.ModelAndCore;
import com.rkb.service.ModelService;
import com.rkb.transform.NetSort;
import com.rkb.util.CreateJsonUtil;
import com.rkb.util.ExcePythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    ModelDao modelDao;
    @Autowired(required = false)
    HttpServletRequest request;
    @Autowired
    DataSetDao dataSetDao;
    @Autowired
    Sequential sequential;
    @Autowired
    CreateJsonUtil createJsonUtil;
    @Autowired
    GooLeNet gooLeNet;
    @Autowired
    VggNet vggNet;
    @Autowired
    ModelCoreDao modelCoreDao;
    @Autowired
    Functional functional;
    @Autowired
    NetSort netSort;
    @Autowired
    DataSet dataSet;
    @Autowired
    ExcePythonUtil excePythonUtil;
    final String GOOGLENET = "GoogLeNet";
    final String VGGNET = "VggNet";
    @Override
    public void addModel(Model model) {
        Long userId = (Long) request.getSession().getAttribute("id");
//        List<DataSet> dataSetNameList = dataSetDao.findDataSetByUserId(userId);
//        model.setDataSetName(dataSetNameList.get(0).getName());
        model.setUserId(userId);
        modelDao.addModel(model);

    }

    @Override
    public void deleteModel(Long id) {
        modelDao.deleteModel(id);
    }


    @Override
    public void updateModel(Model model) {
        modelDao.updateModel(model);
    }

    @Override
    public void updateModelPath(Model model) {
        modelDao.updateModelPath(model);
    }

    @Override
    public Model findModelByName(String name) {
        return modelDao.findModelByName(name);
    }

    @Override
    public List<Model> findAllModelByUserId() {
        Long userId = (Long) request.getSession().getAttribute("id");
        System.out.println(userId);
        return modelDao.findAllModelByUserId(userId);
    }

    @Override
    public String showCore(String netName) {
        return null;
    }

//    @Override
//    public String showCore(String netName) {
//        String Core = null;
//        switch (netName){
//            case GOOGLENET:
//                Core = functional.layers(createJsonUtil.createJson(gooLeNet),null);
//                break;
//            case VGGNET:
//                Core = sequential.layers(createJsonUtil.createJson(vggNet));
//                break;
//                default:
//                    Core = sequential.layers(createJsonUtil.createJson(vggNet));
//                    break;
//        }
//        System.out.println(Core);
//        return Core;
//    }

    @Override
    public String showRealCore(String str,String data,Long id,Long modelId) {
//        System.out.println(hashMap.toString());
        String projectName = findNameById(modelId);
        String core = functional.layers(str,data,id,projectName);
        return core;
    }


    @Override
    public String showDataCore(Long id) {
        return dataSet.getCode(id);
    }
    @Override
    public void trainModel(Long id) throws IOException, InterruptedException {
        excePythonUtil.excePython(id);
    }

    @Override
    public int findLastId() {
        return modelDao.findLastId();
    }
    @Override
    public void deleteCore(Long id) {
        modelCoreDao.deleteCore(id);
    }

    @Override
    public List<ModelAndCore> findAllModelCoreByUserId(Long id) {
        return modelCoreDao.findAllModelCoreByUserId(id);
    }

    @Override
    public String findNameById(Long id) {
        return modelDao.findNameById(id);
    }

    @Override
    public Model findModelById(Long id) {
        return modelDao.findModelById(id);
    }
}
