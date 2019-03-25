package com.rkb.service.impl;

import com.rkb.dao.ModelCoreDao;
import com.rkb.dao.ModelDao;
import com.rkb.dao.PublicModelDao;
import com.rkb.pojo.Model;
import com.rkb.pojo.ModelCore;
import com.rkb.pojo.PublicModel;
import com.rkb.service.ModelCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-17 下午4:25
 */
@Service
public class ModelCoreServiceImpl implements ModelCoreService {
    @Autowired
    ModelCoreDao modelCoreDao;
    @Autowired
    ModelCore modelCore;
    @Autowired
    ModelDao modelDao;
    @Autowired
    PublicModelDao publicModelDao;
    @Autowired
    PublicModel publicModel;
    @Override
    public String findCoreById(Long id) {
        return modelCoreDao.findCoreById(id);
    }

    @Override
    public void saveCore(ModelCore modelCore,Model model) {
        modelDao.addModel(model);
        modelCore.setModelId(model.getId());
        modelCoreDao.saveCore(modelCore);
        modelCore.setId(modelCore.getId());
    }

    @Override
    public void updateCore(ModelCore modelCore) {
        modelCoreDao.updateCore(modelCore);
    }

    @Override
    public List<PublicModel> showCore() {
       List<PublicModel> publicModel = publicModelDao.findPublicModel();
       return publicModel;
    }
    @Override
    public PublicModel copyCore(Model model,ModelCore modelCore,int id) {
        PublicModel publicModel = publicModelDao.findPublicModelById(id);
        modelCore.setNodeCore(publicModel.getCore());
        model.setName(publicModel.getName());
        model.setDescribe_(publicModel.getDecribe_());
        saveCore(modelCore,model);
        return publicModel;
    }
}
