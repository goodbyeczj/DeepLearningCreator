package com.rkb.service.impl;

import com.rkb.dao.DataSetDao;
import com.rkb.dao.PublicDataSetsDao;
import com.rkb.pojo.DataSet;
import com.rkb.pojo.ModelData;
import com.rkb.pojo.PublicDataSets;
import com.rkb.service.DataSetService;
import com.rkb.util.DeleteFileUtil;
import com.rkb.util.FileUtil;
import com.rkb.util.ReadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataSetServiceImpl implements DataSetService {
    @Autowired
    DataSetDao dataSetDao;
    String PATH="/home/lailai/Testing/";
    @Autowired
    ReadFileUtil readFileUtil;
    @Autowired
    PublicDataSetsDao publicDataSetsDao;
    @Override
    public void addData(DataSet dataSet) {
        dataSetDao.addData(dataSet);
    }
    @Override
    public void upload(MultipartFile file,DataSet dataSet){
        String path=PATH+dataSet.getUserId().toString();
        String fileName = file.getOriginalFilename();
        String realName = fileName.split("\\.")[0];
        String realPath = path +"/"+realName;
        dataSet.setName(realName);
        dataSet.setPath(realPath);
        addData(dataSet);
        File filepath = new File(path,fileName);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        } //将上传文件保存到一个目标文件当中
        try {
            file.transferTo(new File(path + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public List<String> getFileName(String path) {
        File f = new File(path);
        List<String> list = new ArrayList<>();
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                list.add(fs.getName());
            } else {
                System.out.println(fs.getName());
            }
        }
        return list;
    }

    @Override
    public List<DataSet> findDataSetByUserId(Long userId) {
        return dataSetDao.findDataSetByUserId(userId);
    }

    @Override
    public void deleteData(DataSet dataSet,Long id) {
        String path = PATH+id.toString()+"/"+dataSet.getName();
        DeleteFileUtil.delete(path);
        dataSetDao.deleteData(dataSet);
    }

    @Override
    public List<String> getFileNames(String path) {
        List<String> list = readFileUtil.getFileName(path);
        return list;
    }

    @Override
    public void compress(MultipartFile file,Long id) throws IOException {
        String path=PATH+id.toString()+"/";
        String fileName = file.getOriginalFilename();
        String realName = fileName.split("\\.")[0];
        String realPath = path+fileName;
        File newFile = new File(realPath);
        FileUtil.unZip(newFile,path);
        DeleteFileUtil.delete(realPath);
    }

    @Override
    public List<PublicDataSets> findPublicDataSets() {
        return publicDataSetsDao.findPublicDataSets();
    }

    @Override
    public PublicDataSets findPublicDataSetsById(DataSet dataSet,int id,Long userId) {
        PublicDataSets publicDataSets = publicDataSetsDao.findPublicDataSetsById(id);
        dataSet.setName(publicDataSets.getName());
        dataSet.setUserId(userId);
        dataSet.setPath(publicDataSets.getPath());
        dataSetDao.addData(dataSet);
        return publicDataSets;
    }

    @Override
    public List<DataSet> findDataSetByUserIdAndName(DataSet dataSet) {
        return dataSetDao.findDataSetByUserIdAndName(dataSet);
    }

    @Override
    public List<ModelData> findAllModelDataById(Long id) {
        return dataSetDao.findAllModelDataById(id);
    }

    @Override
    public void addModelData(ModelData modelData) {
        dataSetDao.addModelData(modelData);
    }
}
