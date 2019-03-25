package com.rkb.layers;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.rkb.bean.net.impl.fun.GooLeNet;
import com.rkb.transform.Key2value;
import com.rkb.transform.NetSort;
import com.rkb.transform.Node;
import com.rkb.util.FileUtil;
import com.rkb.util.PathCreateFile;
import com.rkb.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-9-30 下午5:11
 */
//将json数据装换成keras代码
@Component
public class Functional {
    Node node;
    @Autowired
    NetSort netSort;
    @Autowired
    GooLeNet gooLeNet;
    @Autowired
    FileUtil fileUtil;
    Set<String> set1 = new HashSet<>();
    private final static Logger logger = LoggerFactory.getLogger(Sequential.class);
    @Autowired
    Sequential sequential;
    @Autowired
    PathCreateFile pathCreateFile;
//    String PATH = "/home/lailai/Testing";
    String PATH = PathUtil.getText();
    String PREDICT = "";
    String CALLBACKS ="callbacks";
    String RUN_CORE = "";
    String REAL_RUN_CORE ="";
    String INPUT = "Input";
    String CURRENT = "current";
    String CONCATENATE = "Concatenate";
    String FUN_STARE = " = ";
    String FUN_END = ")";
    String CORE_STARE = "model.add(";
    String CORE_END = "))\r\n";
    String TCORE_STARE = "model.";
    String TCORE_END = ")\r\n";
    String OPTIMIZER_START = " = optimizers.";
    String OPTIMIZER_END = ")\r\n";
    String PREDICT_IMPORT = "from keras.models import load_model\r\nfrom keras.utils import np_utils\r\nimport data\r\n";
    String CORE="";
    String IN = "in";
    String OUT = "out";
    String MODEL = "model";
    String UPMODEL = "Model";
    List<String> INLIST= null;
    List<String> OUTLIST = null;
    String REGEX="[a-zA-Z]*\\d*[a-zA-Z]";
    String IMPORT="import data\nimport show\nimport keras\nfrom keras import metrics\nfrom keras.models import Model\nfrom keras.layers import regularizers,";
    String FIT="fit";
    String COMPILE="compile";
    String EVALUATE="evaluate";
    String REAL_CORE = "";
    String MODEL_PATH = "/home/lailai/model";
    final String newCoreStart = CORE_STARE;
    final String newCoreEnd = CORE_END;
    String DATA_CORE = "";
    String PRE_CORE = "X_train = X_train.astype('float32')\n" +
            "X_test = X_test.astype('float32')\n" +
            "X_train /= 255\n" +
            "X_test /= 255\n" +
            "Y_train = np_utils.to_categorical(y_train, nb_classes)\n" +
            "Y_test = np_utils.to_categorical(y_test, nb_classes)\r\n";
    /**
     * @Description:将json数据转换成layers层代码
     * @Author: Aisake
     * @Date: 18-9-25 上午9:32
     * @Param: [json]
     * @Return: void
     */
    public void init(Long id){
        setCORE("");
        setIMPORT("import data\nimport show\nimport keras\nfrom keras import metrics\nfrom keras.models import Model\nfrom keras.layers import regularizers,");
        setRUN_CORE("");
        pathCreateFile.createFilePath(MODEL_PATH+"/"+id);
    }
    public String layers(String json,String d,Long id,String projectName){
        init(id);
//        fileUtil.write(json,"/home/lailai/Test/json.txt");
        Gson gson = new Gson();
        Map<String, List> hashMap = gson.fromJson(json,Map.class);//有序
        Map<String, String> map = gson.fromJson(d,Map.class);
        List<LinkedTreeMap> nodes = hashMap.get("nodes");
        netSort.init_clear();
        List<Node> list = new ArrayList<>();
        for (LinkedTreeMap<String, LinkedTreeMap> node:nodes) {
            LinkedTreeMap linkedTreeMap = node.get("conventional");
            list.add(setNode(linkedTreeMap));
        }
        netSort.setListall(list);
        netSort.Sort();
        List<String> nameOfList = netSort.getCurrents();
        for (String name:nameOfList) {
            for (LinkedTreeMap<String, LinkedTreeMap> node:nodes) {
                LinkedTreeMap<String,String> linkedTreeMap = node.get("conventional");
                String current = linkedTreeMap.get(CURRENT);
                if (current.equals(name)){
                        CORE += layerNameToCore(current, linkedTreeMap,true,id,projectName);
                        RUN_CORE+= layerNameToCore(current,linkedTreeMap,false,id,projectName);
                        break;
                }
            }
        }
        IMPORT = sequential.nameToImportCore(set1,getIMPORT());
        CORE = createDataCore(map.get("path"),map.get("size"),map.get("pn"),map.get("change"))+PRE_CORE+CORE;
        RUN_CORE = createDataCore(map.get("path"),map.get("size"),map.get("pn"),map.get("change"))+PRE_CORE+RUN_CORE;
        REAL_RUN_CORE = IMPORT+RUN_CORE;
        REAL_CORE = IMPORT+CORE;
        String predict = createPredictCore(map.get("path"),map.get("size"),map.get("pn"),map.get("change"),id,projectName);
        setPREDICT(predict);
        fileUtil.write(REAL_RUN_CORE, PATH+"/"+id.toString()+"/run.py");
        fileUtil.write(REAL_CORE, PATH+"/"+id.toString() +"/core.py");
//        System.out.println(REAL_RUN_CORE);
//        System.out.println(REAL_CORE);
//        System.out.println(UtilPath.getTest_txt());
//        System.out.println(PATH);
        return REAL_CORE;
    }


    public Node setNode(LinkedTreeMap<String,String> linkedTreeMap){
        node = new Node();
        String[] listOfIn;
        String[] listOfOut;
        String current;
        current = linkedTreeMap.get(CURRENT);
        node.setCurrent(current);
        listOfIn = linkedTreeMap.get(IN).split(",");
        INLIST = Arrays.asList(listOfIn);
        node.setLast(INLIST);
        listOfOut = linkedTreeMap.get(OUT).split(",");
        OUTLIST = Arrays.asList(listOfOut);
        node.setNext(OUTLIST);
        return node;
    }
    public String createPredictCore(String path, String size, String p, String c,Long id,String projectName){
        String core = "";
        core = PREDICT_IMPORT+createDataCore(path,size,p,c)+PRE_CORE;
        core +="model = load_model('"+MODEL_PATH+"/"+id+"/"+projectName+".h5"+"')\nmodel.predict(X_test)\n";
        fileUtil.write(core, PATH+"/"+id.toString()+"/predict.py");
        return core;
    }
    public String createDataCore(String path,String size, String p, String c){
        String w;
        String h;
        String[] list = size.split(",");
        w = list[0];
        h = list[1];
        String dataCore = "(X_train, y_train), (X_test, y_test),nb_classes = data.eachFile(\""+path+"\","+w+","+h+","+p+","+c+")\n" ;
        return dataCore;
    }
//    public List<Node> setNodeList(Iterator<Map.Entry<String, LinkedTreeMap>> iterator) {
//        List<Node> list= new ArrayList<>();
//        String[] listOfValue;
//        String keyName = null;
//        while (iterator.hasNext()) {
//            Map.Entry<String, LinkedTreeMap> entry = iterator.next();
//            String key = entry.getKey();
//            LinkedTreeMap value = entry.getValue();
//            Pattern r = Pattern.compile(REGEX);
//            // 现在创建 matcher 对象
//            Matcher m = r.matcher(key);
//            if (m.find()) {
//                keyName = m.group(0);
//                set1.add(keyName);
//            }
//            if (!(keyName.equals(UPMODEL)||keyName.equals(FIT)||keyName.equals(COMPILE)||keyName.equals(EVALUATE))){
//                node = new Node();
//                Iterator<Map.Entry<String, String>> iterator1 = value.entrySet().iterator();
//                while (iterator1.hasNext()) {
//                    Map.Entry<String, String> entry1 = iterator1.next();
//                    String key1 = entry1.getKey();
//                    String value1 = entry1.getValue();
////            System.out.println(key+":"+value);
//                    if (key1.equals(IN)) {
//                        listOfValue = value1.split(",");
//                        INLIST = Arrays.asList(listOfValue);
//                        node.setLast(INLIST);
//                    } else if (key1.equals(OUT)) {
//                        listOfValue = value1.split(",");
//                        OUTLIST = Arrays.asList(listOfValue);
//                        node.setNext(OUTLIST);
//                    }
//                    else if (key1.equals(CURRENT)){
//                        node.setCurrent(value1);
//                    }
//                    else break;
//                }
//                list.add(node);
//            }
//        }
////        for (Node n:list){
////            System.out.println("last"+n.getLast());
////            System.out.println(n.getCurrent());
////            System.out.println("next"+n.getNext());
////        }
//        return list;
//    }
    /**
     * @Description:根据层的名字转换成代码
     * @Author: Aisake
     * @Date: 18-9-25 上午9:37
     * @Param: [entry]
     * @Return: java.lang.String
     */
    public String layerNameToCore(String name,LinkedTreeMap linkedTreeMap,Boolean x,Long id,String projectName){
        String core;
        String keyName = null;
        String in = "";
        String out = null;
        String current = null;
        Gson gson = new Gson();
        asset(name);
        Set<String> set = new HashSet<>();
        set.add("SGD");
        set.add("RMSprop");
        set.add("Adagrad");
        set.add("Adadelta");
        set.add("Adam");
        set.add("Adamax");
        set.add("Nadam");
        set.add("TFOptimizer");
//        Pattern r = Pattern.compile(REGEX);
//        // 现在创建 matcher 对象
//        Matcher m = r.matcher(name);
//        if (m.find()) {
//            keyName = m.group(0);
//        }
        keyName = name.split("_")[0];
        set1.add(keyName);
        if (set.contains(name)){
            core = keyName.toLowerCase()+OPTIMIZER_START+keyName+"(";
        }
        else if (keyName.equals(EVALUATE)|| keyName.equals(FIT)||keyName.equals(COMPILE)){
            if (keyName.equals(FIT)) {
                if (!x)
                    core = "logs_loss = show.LossHistory()\n" +
                            keyName.toLowerCase() + " = " + TCORE_STARE + keyName + "(";
                else
                    core= keyName.toLowerCase()+" = "+TCORE_STARE+keyName+"(";
            }
            else if (keyName.equals(EVALUATE)){
                if (!x)
                    core= "model.save('"+MODEL_PATH+"/"+id+"/"+projectName+".h5"+"')\n"+
                            keyName.toLowerCase()+" = "+TCORE_STARE+keyName+"(";
                else
                    core="model.save('"+MODEL_PATH+"/"+id+"/"+projectName+".h5"+"')\n"+ keyName.toLowerCase()+" = "+TCORE_STARE+keyName+"(";
            }
            else
                core= keyName.toLowerCase()+" = "+TCORE_STARE+keyName+"(";
        }
        else if (keyName.equals(CONCATENATE)){
            core=linkedTreeMap.get(CURRENT)+FUN_STARE+keyName.toLowerCase()+"(";
        }
        else if (keyName.equals(UPMODEL)){
            core=linkedTreeMap.get(CURRENT).toString().toLowerCase()+FUN_STARE+keyName+"(";
        }
        else
            core=linkedTreeMap.get(CURRENT)+FUN_STARE+keyName+"(";
        Iterator<Map.Entry<String,String>> iterator1 = linkedTreeMap.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String,String> entry1 = iterator1.next();
            String key = entry1.getKey();
            String value = entry1.getValue();
            System.out.println(key+":"+value);
            if (!value.equals("")||(keyName.equals(CONCATENATE)&&key.equals("kwargs"))||key.equals("outputs")) {
                if (key.equals(IN)) {
                    if (value!=null)
                        in = value;
                } else if (key.equals(OUT)) {
                    out = value;
                } else if (key.equals(CURRENT)) {
                    current = value;
                } else {
                    if(keyName.equals(CONCATENATE)&&key.equals("kwargs")){
                            value = "["+in+"]";
                    }
                    if (key!=null)
                        core += Key2value.key2value(key, value, sequential.connectMethodName(keyName),x);
                }
                if (iterator1.hasNext()) {
                    if (!key.equals(IN) && !key.equals(OUT) && !key.equals(CURRENT)){
                        if (!(key.equals(CALLBACKS)&&x))
                            core+=",";
                    }
                }
            }
        }
        if (core.endsWith(",")){
            core=core.substring(0,core.length()-1);
        }
        if (set.contains(name)){
            core+=OPTIMIZER_END;
        }
        else if (keyName.equals(EVALUATE)||keyName.equals(FIT)||keyName.equals(COMPILE)||keyName.equals(INPUT)||keyName.equals(UPMODEL)||keyName.equals(CONCATENATE)){
            core+=FUN_END+"\r\n";
        }
        else{
            core+=FUN_END+"("+in+")\r\n";
        }
        return core;
    }

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

    public void setCORE(String CORE) {
        this.CORE = CORE;
    }

    public String getIMPORT() {
        return IMPORT;
    }

    public void setIMPORT(String IMPORT) {
        this.IMPORT = IMPORT;
    }
    public void setRUN_CORE(String RUN_CORE){
        this.RUN_CORE = RUN_CORE;
    }

    public String getPREDICT() {
        return PREDICT;
    }

    public void setPREDICT(String PREDICT) {
        this.PREDICT = PREDICT;
    }
}
