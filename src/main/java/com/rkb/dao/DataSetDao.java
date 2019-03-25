package com.rkb.dao;

import com.rkb.pojo.DataSet;
import com.rkb.pojo.ModelData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSetDao {
    void addData(DataSet dataSet);
    void addPath(String path);
    List<DataSet> findDataSetByUserId(Long userId);
    void deleteData(DataSet dataSet);
    List<DataSet> findDataSetByUserIdAndName(DataSet dataSet);
    List<ModelData> findAllModelDataById(Long id);
    void addModelData(ModelData modelData);
}
