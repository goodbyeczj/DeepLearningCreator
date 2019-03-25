package com.rkb.layers;


import com.rkb.transform.DataMap;
import com.rkb.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@Component
public class Sequential {
    @Autowired
    FileUtil fileUtil;
    private final static Logger logger = LoggerFactory.getLogger(Sequential.class);
    String CORE_STARE = "model.add(";
    String CORE_END = "))\r\n";
    String MODEL = "Model";
    String TCORE_STARE = "model.";
    String TCORE_END = ")\r\n";
    String OPTIMIZER_START = " = optimizers.";
    String OPTIMIZER_END = ")\r\n";
    String CORE="model = Sequential()\r\n";
    String REGEX="[a-zA-Z]*\\d*[a-zA-Z]";
    String IMPORT="import data\nimport keras\nfrom keras.models import Sequential\r\nfrom keras.layers import ";
    String FIT="fit";
    String COMPILE="compile";
    String CONCATENATE = "Concatenate";
    String EVALUATE="evaluate";
    String IMPORTNULL = "";
    String IMPORT_APP_CORE ="";
    String REAL_CORE = "";
    String SORC = "sorc = ";
    String NEW_IMPORT_OPTIMIZER = "from keras.optimizers import ";
    String NEW_IMPORT_APP = "from keras.applications.";
    final String newCoreStart = CORE_STARE;
    final String newCoreEnd = CORE_END;
    String PARM = "";
    String DATA_CORE = "";
    String IMPORT_DATA_CORE = "";
    String IMPORT_BACKEND = "";
    String PRE_CORE = "(X_train, y_train), (X_test, y_test),nb_classes = data.eachFile(\"/home/lailai/DLS/DLS-master/data-test/dataset-image2d-mnist-small-10k\",224,224,1,0.8)\n" +
            "X_train = X_train.astype('float32')\n" +
            "X_test = X_test.astype('float32')\n" +
            "X_train /= 255\n" +
            "X_test /= 255\n" +
            "Y_train = np_utils.to_categorical(y_train, nb_classes)\n" +
            "Y_test = np_utils.to_categorical(y_test, nb_classes)\r\n";
    String IMPORT_UTIL = "from keras.utils import np_utils\r\n";
    /**
     * @Description:将json数据转换成layers层代码
     * @Author: Aisake
     * @Date: 18-9-25 上午9:32
     * @Param: [json]
     * @Return: void
     */
//    public String layers(String json){
////        System.out.println(json);
//        Gson gson = new Gson();
//        Map<String, LinkedTreeMap> hashMap = gson.fromJson(json,LinkedTreeMap.class);//有序
////        HashMap<String, LinkedHashMap<String,String>> hashMap = JSONObject.parseObject(json,HashMap.class);//无序
////        System.out.println(hashMap);
//        Iterator<Map.Entry<String, LinkedTreeMap>> iterator= hashMap.entrySet().iterator();
//        Set<String> set = new HashSet<>();
//        while(iterator.hasNext())
//        {
//            Map.Entry<String,LinkedTreeMap> entry = iterator.next();
//            String key = entry.getKey();
//            LinkedTreeMap value = entry.getValue();
//            Pattern r = Pattern.compile(REGEX);
//            // 现在创建 matcher 对象
//            Matcher m = r.matcher(key);
//            if (m.find()) {
//                String keyName = m.group(0);
//                set.add(keyName);
//                CORE += layerNameToCore(keyName,value);
//            }
//        }
//        IMPORT = nameToImportCore(set,IMPORT);
//        CORE = DATA_CORE + PRE_CORE+CORE;
//        REAL_CORE = IMPORT+PARM+CORE;
//        fileUtil.write(REAL_CORE,"./run.py");
//        logger.info(REAL_CORE);
//        return REAL_CORE;
//    }

    public String nameToImportCore(Set<String> set,String importCore){
        String IMPORT_OPTIMIZER = "from keras.optimizers import ";
        String IMPORT_APP = "from keras.applications.";
        Iterator<String> iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            String importName = iterator1.next();
            Boolean special = importName.equals(MODEL)|importName.equals(FIT) | importName.equals(COMPILE) | importName.equals(EVALUATE);
            Boolean optimizers = importName.equals("SGD")|importName.equals("RMSprop")|importName.equals("Adagrad")|importName.equals("Adadelta")|importName.equals("Adam")|importName.equals("Adamax")|importName.equals("Nadam")|importName.equals("TFOptimizer");
            Boolean application = importName.equals("MobileNetV2")||importName.equals("NASNet")||importName.equals("DenseNet")||importName.equals("MobileNet")||importName.equals("InceptionResNetV2")||importName.equals("InceptionV3")||importName.equals("VGG19")||importName.equals("VGG16")||importName.equals("Xception")||importName.equals("ResNet50");
            if (special) {
               IMPORTNULL = "";
            }
            else if (optimizers){
                IMPORT_OPTIMIZER += importName;
            }
            else if (importName.equals(CONCATENATE)){
                importCore+= importName.toLowerCase();
            }
            else if (application){
                IMPORT_APP +=importName.toLowerCase()+" import "+importName;
            }
            else
                importCore += importName;
            if (iterator1.hasNext()) {
                if (special) {
                    importCore += "";
                }
                else if (optimizers){
                    IMPORT_OPTIMIZER+=",";
                }
                else if (application){
                    IMPORT_APP +=",";
                }
                else
                    importCore += ",";
            }
        }
        if (importCore.endsWith(",")){
            importCore = importCore.substring(0,importCore.length()-1);
        }
        if (IMPORT_OPTIMIZER.endsWith(",")){
            IMPORT_OPTIMIZER = IMPORT_OPTIMIZER.substring(0,IMPORT_OPTIMIZER.length()-1);
        }
        if (IMPORT_APP.endsWith(",")){
            IMPORT_APP = IMPORT_APP.substring(0,IMPORT_APP.length()-1);
        }
        if (IMPORT_OPTIMIZER.equals(NEW_IMPORT_OPTIMIZER)&&IMPORT_APP.equals(NEW_IMPORT_APP))
            importCore = importCore+"\r\n"+IMPORT_DATA_CORE+IMPORT_BACKEND+IMPORT_UTIL;
        else if (IMPORT_OPTIMIZER.equals(NEW_IMPORT_OPTIMIZER)&&!IMPORT_APP.equals(NEW_IMPORT_APP)){
            importCore = importCore+"\r\n"+IMPORT_APP+"\r\n"+IMPORT_DATA_CORE+IMPORT_BACKEND+IMPORT_UTIL;
        }
        else if (!IMPORT_OPTIMIZER.equals(NEW_IMPORT_OPTIMIZER)&&IMPORT_APP.equals(NEW_IMPORT_APP)){
            importCore = importCore+"\r\n"+IMPORT_OPTIMIZER+"\r\n"+IMPORT_DATA_CORE+IMPORT_BACKEND+IMPORT_UTIL;
        }
        else
            importCore = importCore+"\r\n"+IMPORT_OPTIMIZER+"\r\n"+IMPORT_APP+"\r\n"+IMPORT_DATA_CORE+IMPORT_BACKEND+IMPORT_UTIL;
        return importCore;
    }
    /**
     * @Description:根据层的名字转换成代码
     * @Author: Aisake
     * @Date: 18-9-25 上午9:37
     * @Param: [entry]
     * @Return: java.lang.String
     */
//    public String layerNameToCore(String name,LinkedTreeMap linkedTreeMap){
//        String core;
//        asset(name);
//        Set<String> set = new HashSet<>();
//        set.add("SGD");
//        set.add("RMSprop");
//        set.add("Adagrad");
//        set.add("Adadelta");
//        set.add("Adam");
//        set.add("Adamax");
//        set.add("Nadam");
//        set.add("TFOptimizer");
//        if (set.contains(name)){
//            core = name.toLowerCase()+OPTIMIZER_START+name+"(";
//        }
//        else if (name.equals(EVALUATE)){
//            core= name.toLowerCase()+" = "+CORE_STARE+name+"(";
//        }
//        else
//            core=CORE_STARE+name+"(";
//        Iterator<Map.Entry<String,String>> iterator1 = linkedTreeMap.entrySet().iterator();
//        while (iterator1.hasNext()){
//            Map.Entry<String,String> entry1 = iterator1.next();
//            String key = entry1.getKey();
//            String value = entry1.getValue();
////            System.out.println(key+":"+value);
//            if (!key.equals("current"))
//                core += Key2value.key2value(key,value, connectMethodName(name));
//            if(iterator1.hasNext()){
//                if (!key.equals("current"))
//                    core+=",";
//            }
//        }
//        if (set.contains(name)){
//            core+=OPTIMIZER_END;
//        }
//        else
//            core+=CORE_END;
//        return core;
//    }

    /**
     * @Description:获取方法名
     * @Author: Aisake
     * @Date: 18-9-25 上午9:38
     * @Param: []
     * @Return: java.lang.String
     */
    public String getMethodName(){
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];
        String methodName = e.getMethodName();
        return methodName;
    }
    /**
     * @Description:拼接方法名称
     * @Author: Aisake
     * @Date: 18-9-25 上午9:38
     * @Param: [methodName]
     * @Return: java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String,String> connectMethodName(String methodName) {
        DataMap dataMap = new DataMap();
        Class classDataMap = dataMap.getClass();
        String changeName = "get"+methodName;
        Method method = null;
        try {
            method = classDataMap.getMethod(changeName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Map<String,String> map = null;
        try {
            map = (Map) method.invoke(dataMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * @Description:判断key是否是FIT与COMPILE
     * @Author: Aisake
     * @Date: 18-9-25 下午7:15
     * @Param: [key]
     * @Return: void
     */
    public void asset(String key){
        if (key.equals(FIT)||key.equals(COMPILE)||key.equals(EVALUATE)){
            CORE_STARE = TCORE_STARE;
            CORE_END = TCORE_END;
        }
        else {
            if (newCoreStart!=CORE_STARE&&newCoreEnd!=CORE_END){
                CORE_STARE = newCoreStart;
                CORE_END = newCoreEnd;
            }
        }
    }

}
