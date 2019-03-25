package com.rkb.service;

import com.rkb.pojo.Model;
import com.rkb.pojo.ModelCore;
import com.rkb.pojo.PublicModel;

import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-17 下午4:25
 */

public interface ModelCoreService {
    String findCoreById(Long id);
    void saveCore(ModelCore modelCore, Model model);
    void updateCore(ModelCore modelCore);
    List<PublicModel> showCore();
    PublicModel copyCore(Model model, ModelCore modelCore, int id);
}
