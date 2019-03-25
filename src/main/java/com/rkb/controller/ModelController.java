package com.rkb.controller;

import com.rkb.bean.Train;
import com.rkb.layers.Functional;
import com.rkb.pojo.DataSet;
import com.rkb.pojo.Model;
import com.rkb.pojo.ModelAndCore;
import com.rkb.service.DataSetService;
import com.rkb.service.ModelCoreService;
import com.rkb.service.ModelService;
import com.rkb.transform.MapToGson;
import com.rkb.util.CreateShowUtil;
import com.rkb.util.ExcePythonUtil;
import com.rkb.util.PathUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("model")
public class ModelController {
    @Autowired
    ModelService modelService;
    @Autowired
    ModelCoreService modelCoreService;
    @Autowired(required = false)
    HttpServletRequest request;
    @Autowired
    DataSetService dataSetService;
    @Autowired
    Functional functional;
    @Autowired
    CreateShowUtil createShowUtil;
    @Autowired
    ExcePythonUtil excePythonUtil;
    @Autowired
    Train train;
    String value = "{'path':'dataset-image2d-mnist-small-10k','size':'28,28','change':'0.8','pn':'1'}";
    String core = null;
    String hy = null;
    Boolean flag = false;
    Long modelId = 1L;
//    String DATAPATH = "/home/lailai/Testing";
    String PATH = PathUtil.getText();
    String MODEL_PATH = "/home/lailai/model";
    Long threadId;
    @RequestMapping("/add")
    public void addModel(PrintWriter out,Model model){
        modelService.addModel(model);
        int lastId = modelService.findLastId();
        out.print(lastId);
//        modelAndView.setViewName("redirect:manage");
        out.close();
//        return modelAndView;
    }
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteModel(@PathVariable("id") Long id){
        modelService.deleteModel(id);
        modelService.deleteCore(id);
        return "";
    }
    @RequestMapping("/edit")
    public ModelAndView editModel(ModelAndView modelAndView,int id){
        modelAndView.addObject("id",id);
        modelAndView.setViewName("modelEdit");
        return modelAndView;
    }
    @RequestMapping("/update")
    public ModelAndView updateModel(ModelAndView modelAndView,Model model){
        modelService.updateModel(model);
        modelAndView.setViewName("modelManager");
        return modelAndView;
    }
    @RequestMapping("/search")
    public ModelAndView findModelByName(ModelAndView modelAndView,String name){
        Model model = modelService.findModelByName(name);
        modelAndView.addObject("model",model);
        modelAndView.setViewName("modelManager");
        return modelAndView;
    }
    @RequestMapping("/manage")
    public String findModelByName(org.springframework.ui.Model model, HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("id");
        List<ModelAndCore> listCore = modelService.findAllModelCoreByUserId(userId);
        List<DataSet> dataSets = dataSetService.findDataSetByUserId(userId);
        model.addAttribute("list",listCore);
        model.addAttribute("dataSets",dataSets);
        return "chart";
    }
    @RequestMapping("/showCore")
    public ModelAndView showCore(ModelAndView modelAndView,String netName){
        String core = modelService.showCore(netName);
        modelAndView.addObject("core",core);
        modelAndView.setViewName("showCore");
        return modelAndView;
    }
    @RequestMapping("/showRealCore")
    @ResponseBody
    public String showRealCore(String str, HashMap map, HttpServletRequest request){
//        System.out.println(str);
        Long pid = (Long) request.getSession().getAttribute("pid");
        Long id = (Long) request.getSession().getAttribute("id");
        Model model = modelService.findModelById(modelId);
        if (model!=null){
            String data = model.getDataInfo();

            core = modelService.showRealCore(str,data,id,modelId);
//            modelCore.setModelId(modelId);
//            modelCore.setUserId(id);
//            modelCore.setNodeCore(str);
//            modelCoreService.updateCore(modelCore);
            map.put("core",core);
        }
        else {
            map.put("core","please completed input data");
        }
        map.put("pid",pid);
//        modelAndView.setView(new MappingJackson2JsonView());

        return MapToGson.MapToGson(map);
    }
    @RequestMapping("/core/{id}")
    @ResponseBody
    public String findCoreById(@PathVariable("id") Long id) {
        request.getSession().setAttribute("pid",id);
        String core = modelCoreService.findCoreById(id);
        if (core==null){
            core="{\"nodes\":[],\"edges\":[]}";
        }
        Long userId = (Long) request.getSession().getAttribute("id");
        Model model = modelService.findModelById(modelId);
        if (model!=null){
            String data = model.getDataInfo();
            if (data==null){
                data = "{\"path\":\"/home/lailai/public/dataset-image2d-mnist-small-10k\",\"size\":\"28,28\",\"change\":\"0.8\",\"pn\":\"1\"}";
            }
            modelService.showRealCore(core,data,userId,modelId);
        }
        return core;
    }
    @RequestMapping("/show_core")
    public void showCore(PrintWriter out){
        Long id = (Long) request.getSession().getAttribute("id");
        createShowUtil.getCore(id);
        String core = modelService.showDataCore(id);
        out.print(core);
        out.flush();
        out.close();
    }
    @RequestMapping("/showPredict_core")
    public void showPredict(PrintWriter out){
        String core = functional.getPREDICT();
        out.print(core);
        out.flush();
        out.close();
    }
    @RequestMapping("/commitData")
    @ResponseBody
    public void showDataCore(String str){
        Model model = modelService.findModelById(modelId);
        model.setDataInfo(str);
        modelService.updateModel(model);
    }
    @RequestMapping("/showData")
    @ResponseBody
    public String showData(){
        Model model = modelService.findModelById(modelId);
        String data;
        String dataInfo = model.getDataInfo();
        if (dataInfo==null){
            data= "{\"path\":\"/home/lailai/public/dataset-image2d-mnist-small-10k\",\"size\":\"28,28\",\"change\":\"0.8\",\"pn\":\"1\"}";
        }
        else {
            data = model.getDataInfo();
        }
        return data;
    }
    @RequestMapping("/to_flowchart")
    public ModelAndView toFlowChart(ModelAndView modelAndView){
        modelAndView.setViewName("html/flowchart.html");
        return modelAndView;
    }
    @RequestMapping("/to_classic_net")
    public ModelAndView toClassicNet(ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/classic_net.html");
        return modelAndView;
    }
    @RequestMapping("/to_submit")
    public ModelAndView toSubmit(ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/submit.jsp");
        return modelAndView;
    }
    @RequestMapping("/changHy")
    public void changHy(PrintWriter out,String con){
        hy = con;
        out.close();
    }
    @RequestMapping("/train")
    @ResponseBody
    public String trainModel(Model model) throws InterruptedException, IOException {
        Long userId = (Long) request.getSession().getAttribute("id");
//        setThreadId(excePythonUtil.getId());
//        excePythonUtil.setUserId(userId);
//        excePythonUtil.start();
//        Thread.sleep(3000);
//        thread.interrupt();
        int test = excePythonUtil.excePython(userId);
        if (test==0){
            String projectName = modelService.findNameById(modelId);
            String modelPath = projectName+".h5";
            model.setModelPath(modelPath);
            model.setId(modelId);
            modelService.updateModelPath(model);
            return "success";
        }
        else if (test==137){
            return "interrupt";
        }
        else {
            return "error";
        }
        }
    @RequestMapping("/stop")
    @ResponseBody
    public String stopTrain(){
//        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
//        ThreadInfo info = tmx.getThreadInfo(getThreadId());
//        System.out.println(info.getThreadState());
//        Thread thread = ThreadUtil.findThread(threadId);
//        thread.interrupt();
        excePythonUtil.stopTraining();

        return "success";
    }
    @RequestMapping("/download")
    public ResponseEntity<byte[]> export() throws IOException {
        String fileName = "run.py";
        Long id = (Long) request.getSession().getAttribute("id");
        String filePath = PATH+"/"+id.toString()+"/core.py";
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
    @RequestMapping("/downloadPredict")
    public ResponseEntity<byte[]> downloadPredict() throws IOException {
        String fileName = "predict.py";
        Long id = (Long) request.getSession().getAttribute("id");
        String filePath = PATH+"/"+id.toString()+"/predict.py";
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
    @RequestMapping("/downloadData")
    public ResponseEntity<byte[]> downloadData() throws IOException {
        String fileName = "data.py";
        Long id = (Long) request.getSession().getAttribute("id");
        String filePath = PATH+"/"+id.toString()+"/data.py";
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
    @RequestMapping("/downloadModel")
    public ResponseEntity<byte[]> downloadModel(String fileName) throws IOException {
        Long id = (Long) request.getSession().getAttribute("id");
        String filePath = MODEL_PATH+"/"+id.toString()+"/"+fileName;
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
    @RequestMapping("/projectName")
    @ResponseBody
    public String findNameById(Long id){
        modelId = id;
        String name = modelService.findNameById(id);
        return name;
    }
    @RequestMapping("/findTrainedModel")
    @ResponseBody
    public String findTrainedModel(HashMap map){
        List<Model> trainedModels = modelService.findAllModelByUserId();
        map.put("trainedModels",trainedModels);
        return MapToGson.MapToGson(map);
    }
}
