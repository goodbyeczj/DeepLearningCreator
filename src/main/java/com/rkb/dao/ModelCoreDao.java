package com.rkb.dao;

import com.rkb.pojo.ModelAndCore;
import com.rkb.pojo.ModelCore;

import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-17 下午4:18
 */

public interface ModelCoreDao {
    String findCoreById(Long id);
    void saveCore(ModelCore modelCore);
    void deleteCore(Long id);

    List<ModelAndCore> findAllModelCoreByUserId(Long id);
    void updateCore(ModelCore modelCore);
}
