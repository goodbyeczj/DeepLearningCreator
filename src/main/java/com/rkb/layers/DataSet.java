package com.rkb.layers;

import com.rkb.util.FileUtil;
import com.rkb.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-10-15 下午12:24
 */
@Component
public class DataSet {
    @Autowired
    FileUtil fileUtil;
//    String PATH = "/home/lailai/Testing";
    String PATH = PathUtil.getText();
    String CODE = "import random\n" +
            "import cv2\n" +
            "import os\n" +
            "import numpy as np\n" +
            "def eachFile(filepath, w, l, t,ratio):\n" +
            "    x_train = []\n" +
            "    y_train = []\n" +
            "    x_test = []\n" +
            "    y_test = []\n" +
            "    pathDir = os.listdir(filepath)\n" +
            "    lists = []\n" +
            "    for allDir in pathDir:\n" +
            "        pathDir2 = os.listdir(filepath+'/'+allDir)\n" +
            "        for list in pathDir2:\n" +
            "            dist = {}\n" +
            "            if cv2.imread(filepath+'/'+allDir+'/'+list).shape[0] == w and cv2.imread(filepath+'/'+allDir+'/'+list).shape[1] == l and t == 1:\n" +
            "                img = cv2.imread(filepath + '/' + allDir + '/' + list, 0)\n" +
            "            elif cv2.imread(filepath+'/'+allDir+'/'+list).shape[0] == w and cv2.imread(filepath+'/'+allDir+'/'+list).shape[1] == l and t == 3:\n" +
            "                img = cv2.imread(filepath + '/' + allDir + '/' + list, 1)\n" +
            "            else:\n" +
            "                if t == 1:\n" +
            "                    img = cv2.resize(cv2.imread(filepath + '/' + allDir + '/' + list, 0), (w, l))\n" +
            "                else:\n" +
            "                    img = cv2.resize(cv2.imread(filepath + '/' + allDir + '/' + list, 1), (w, l))\n" +
            "            if t == 1:\n" +
            "                dist[allDir] = np.array(img).reshape(w,l,1)\n" +
            "            else:\n" +
            "                dist[allDir] = img\n" +
            "            lists.append(dist)\n" +
            "    random.shuffle(lists)\n" +
            "    num = int(len(lists)*ratio)\n" +
            "    list_train = lists[ : num]\n" +
            "    list_test = lists[num : ]\n" +
            "    for i in list_train:\n" +
            "        for key in i:\n" +
            "            x_train.append(i[key])\n" +
            "            y_train.append(key)\n" +
            "    for i in list_test:\n" +
            "        for key in i:\n" +
            "            x_test.append(i[key])\n" +
            "            y_test.append(key)\n" +
            "    x_train = np.array(x_train)\n" +
            "    x_test = np.array(x_test)\n" +
            "    y_test = np.array(y_test)\n" +
            "    y_train = np.array(y_train)\n" +
            "    return (x_train,y_train),(x_test,y_test),len(pathDir)\n\r\n";
    public String getCode(Long id){
        fileUtil.write(CODE,PATH+"/"+id.toString() +"/data.py");
        return CODE;
    }
}
