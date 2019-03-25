package com.rkb.service;

import com.rkb.pojo.Model;
import com.rkb.pojo.ModelAndCore;

import java.io.IOException;
import java.util.List;

public interface ModelService {
    void addModel(Model model);
    void deleteModel(Long id);
    void updateModel(Model model);
    void updateModelPath(Model model);
    Model findModelByName(String name);
    List<Model> findAllModelByUserId();
    String showCore(String netName);
    String showRealCore(String str, String data, Long id, Long modelId);
    String showDataCore(Long id);
    void trainModel(Long id) throws IOException, InterruptedException;
    int findLastId();
    String findNameById(Long id);
    void deleteCore(Long id);
    Model findModelById(Long id);
    List<ModelAndCore> findAllModelCoreByUserId(Long id);
}
