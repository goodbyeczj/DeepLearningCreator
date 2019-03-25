package com.rkb.dao;

import com.rkb.pojo.PublicDataSets;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-13 下午6:13
 */
@Repository
public interface PublicDataSetsDao {
    List<PublicDataSets> findPublicDataSets();
    PublicDataSets findPublicDataSetsById(int id);
}
