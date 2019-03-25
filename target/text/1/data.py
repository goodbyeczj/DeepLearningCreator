import random
import cv2
import os
import numpy as np
def eachFile(filepath, w, l, t,ratio):
    x_train = []
    y_train = []
    x_test = []
    y_test = []
    pathDir = os.listdir(filepath)
    lists = []
    for allDir in pathDir:
        pathDir2 = os.listdir(filepath+'/'+allDir)
        for list in pathDir2:
            dist = {}
            if cv2.imread(filepath+'/'+allDir+'/'+list).shape[0] == w and cv2.imread(filepath+'/'+allDir+'/'+list).shape[1] == l and t == 1:
                img = cv2.imread(filepath + '/' + allDir + '/' + list, 0)
            elif cv2.imread(filepath+'/'+allDir+'/'+list).shape[0] == w and cv2.imread(filepath+'/'+allDir+'/'+list).shape[1] == l and t == 3:
                img = cv2.imread(filepath + '/' + allDir + '/' + list, 1)
            else:
                if t == 1:
                    img = cv2.resize(cv2.imread(filepath + '/' + allDir + '/' + list, 0), (w, l))
                else:
                    img = cv2.resize(cv2.imread(filepath + '/' + allDir + '/' + list, 1), (w, l))
            if t == 1:
                dist[allDir] = np.array(img).reshape(w,l,1)
            else:
                dist[allDir] = img
            lists.append(dist)
    random.shuffle(lists)
    num = int(len(lists)*ratio)
    list_train = lists[ : num]
    list_test = lists[num : ]
    for i in list_train:
        for key in i:
            x_train.append(i[key])
            y_train.append(key)
    for i in list_test:
        for key in i:
            x_test.append(i[key])
            y_test.append(key)
    x_train = np.array(x_train)
    x_test = np.array(x_test)
    y_test = np.array(y_test)
    y_train = np.array(y_train)
    return (x_train,y_train),(x_test,y_test),len(pathDir)

