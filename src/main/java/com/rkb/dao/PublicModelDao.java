package com.rkb.dao;

import com.rkb.pojo.PublicModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-13 下午6:14
 */
@Repository
public interface PublicModelDao {
    List<PublicModel> findPublicModel();
    PublicModel findPublicModelById(int id);
}
