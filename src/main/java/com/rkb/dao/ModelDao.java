package com.rkb.dao;

import com.rkb.pojo.Model;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelDao {
    void addModel(Model model);
    void deleteModel(long id);
    void updateModel(Model model);
    void updateModelPath(Model model);
    Model findModelByName(String name);
    String findNameById(Long id);
    List<Model> findAllModelByUserId(Long userId);
    int findLastId();
    Model findModelById(Long id);
}
