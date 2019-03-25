package com.rkb.service;

import com.rkb.pojo.DataSet;
import com.rkb.pojo.ModelData;
import com.rkb.pojo.PublicDataSets;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DataSetService {
      void addData(DataSet dataSet);
      void upload(MultipartFile file, DataSet dataSet);
      List<DataSet> findDataSetByUserId(Long userId);
      void deleteData(DataSet dataSet, Long userId);
      List<String> getFileNames(String path);
      void compress(MultipartFile file, Long id) throws IOException;
      List<PublicDataSets> findPublicDataSets();
      PublicDataSets findPublicDataSetsById(DataSet dataSet, int id, Long userId);
      List<DataSet> findDataSetByUserIdAndName(DataSet dataSet);
      List<ModelData> findAllModelDataById(Long id);
      void addModelData(ModelData modelData);
}
