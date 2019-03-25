package com.rkb.bean.net.impl.seq;

import com.rkb.bean.gson.*;
import com.rkb.bean.net.Net;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-10-19 上午10:46
 */
@Component
public class VggNet<T> implements Net {

    @Override
    public Set<Object> CreateNet() {
        Set<Object> treeSet = new ListOrderedSet<>();

        Conv2D conv2d1 = new Conv2D();
        conv2d1.setCurrent("conv2d1");
        conv2d1.setFilters("64");
        conv2d1.setKernelSize("(3,3)");
        conv2d1.setActivation("relu");
        conv2d1.setInputShape("(224,224,1)");
        conv2d1.setPadding("same");
        conv2d1.setData_format("channels_last");
        conv2d1.setStrides("(1,1)");


        Conv2D conv2d2 = new Conv2D();
        conv2d2.setCurrent("conv2d2");
        conv2d2.setFilters("64");
        conv2d2.setKernelSize("(3,3)");
        conv2d2.setActivation("relu");
        conv2d2.setPadding("same");
        conv2d2.setStrides("(1,1)");



        MaxPooling2D maxPooling2D1 = new MaxPooling2D();
        maxPooling2D1.setCurrent("maxPooling2D1");
        maxPooling2D1.setPoolSize("(2,2)");
        maxPooling2D1.setStrides("(2,2)");



        Conv2D conv2d3 = new Conv2D();
        conv2d3.setCurrent("conv2d3");
        conv2d3.setFilters("128");
        conv2d3.setKernelSize("(3,3)");
        conv2d3.setActivation("relu");
        conv2d3.setPadding("same");
        conv2d3.setStrides("(1,1)");



        Conv2D conv2d4 = new Conv2D();
        conv2d4.setCurrent("conv2d4");
        conv2d4.setFilters("128");
        conv2d4.setKernelSize("(3,3)");
        conv2d4.setActivation("relu");
        conv2d4.setPadding("same");
        conv2d4.setStrides("(1,1)");


        MaxPooling2D maxPooling2D2 = new MaxPooling2D();
        maxPooling2D2.setCurrent("maxPooling2D2");
        maxPooling2D2.setPoolSize("(2,2)");
        maxPooling2D2.setStrides("(2,2)");



        Conv2D conv2d5 = new Conv2D();
        conv2d5.setCurrent("conv2d5");
        conv2d5.setFilters("256");
        conv2d5.setKernelSize("(3,3)");
        conv2d5.setActivation("relu");
        conv2d5.setPadding("same");
        conv2d5.setStrides("(1,1)");





        Conv2D conv2d6 = new Conv2D();
        conv2d6.setCurrent("conv2d6");
        conv2d6.setFilters("256");
        conv2d6.setKernelSize("(3,3)");
        conv2d6.setActivation("relu");
        conv2d6.setPadding("same");
        conv2d6.setStrides("(1,1)");



        Conv2D conv2d7 = new Conv2D();
        conv2d7.setCurrent("conv2d7");
        conv2d7.setFilters("256");
        conv2d7.setKernelSize("(3,3)");
        conv2d7.setActivation("relu");
        conv2d7.setPadding("same");
        conv2d7.setStrides("(1,1)");


        MaxPooling2D maxPooling2D3 = new MaxPooling2D();
        maxPooling2D3.setCurrent("maxPooling2D3");
        maxPooling2D3.setPoolSize("(2,2)");
        maxPooling2D3.setStrides("(2,2)");



        Conv2D conv2d8 = new Conv2D();
        conv2d8.setCurrent("conv2d8");
        conv2d8.setFilters("512");
        conv2d8.setKernelSize("(3,3)");
        conv2d8.setActivation("relu");
        conv2d8.setPadding("same");
        conv2d8.setStrides("(1,1)");





        Conv2D conv2d9 = new Conv2D();
        conv2d9.setCurrent("conv2d9");
        conv2d9.setFilters("512");
        conv2d9.setKernelSize("(3,3)");
        conv2d9.setActivation("relu");
        conv2d9.setPadding("same");
        conv2d9.setStrides("(1,1)");



        Conv2D conv2d10 = new Conv2D();
        conv2d10.setCurrent("conv2d10");
        conv2d10.setFilters("512");
        conv2d10.setKernelSize("(3,3)");
        conv2d10.setActivation("relu");
        conv2d10.setPadding("same");
        conv2d10.setStrides("(1,1)");


        MaxPooling2D maxPooling2D4 = new MaxPooling2D();
        maxPooling2D4.setCurrent("maxPooling2D4");
        maxPooling2D4.setPoolSize("(2,2)");
        maxPooling2D4.setStrides("(2,2)");




        Conv2D conv2d11 = new Conv2D();
        conv2d11.setCurrent("conv2d11");
        conv2d11.setFilters("512");
        conv2d11.setKernelSize("(3,3)");
        conv2d11.setActivation("relu");
        conv2d11.setPadding("same");
        conv2d11.setStrides("(1,1)");





        Conv2D conv2d12 = new Conv2D();
        conv2d12.setCurrent("conv2d12");
        conv2d12.setFilters("512");
        conv2d12.setKernelSize("(3,3)");
        conv2d12.setActivation("relu");
        conv2d12.setPadding("same");
        conv2d12.setStrides("(1,1)");



        Conv2D conv2d13 = new Conv2D();
        conv2d13.setCurrent("conv2d13");
        conv2d13.setFilters("512");
        conv2d13.setKernelSize("(3,3)");
        conv2d13.setActivation("relu");
        conv2d13.setPadding("same");
        conv2d13.setStrides("(1,1)");


        MaxPooling2D maxPooling2D5 = new MaxPooling2D();
        maxPooling2D5.setCurrent("maxPooling2D5");
        maxPooling2D5.setPoolSize("(2,2)");
        maxPooling2D5.setStrides("(2,2)");




        Flatten flatten = new Flatten();
        flatten.setCurrent("flatten");





        Dense dense1 = new Dense();
        dense1.setCurrent("dense1");
        dense1.setUnits("256");
        dense1.setActivation("relu");



        Dropout dropout1 = new Dropout();
        dropout1.setCurrent("dropout1");
        dropout1.setRate("0.5");



        Dense dense2 = new Dense();
        dense2.setCurrent("dense2");
        dense2.setUnits("256");
        dense2.setActivation("relu");



        Dropout dropout2 = new Dropout();
        dropout2.setCurrent("dropout2");
        dropout2.setRate("0.5");


        Dense dense3 = new Dense();
        dense3.setCurrent("dense3");
        dense3.setUnits("10");
        dense3.setActivation("relu");

        Compile compile = new Compile();
        compile.setCurrent("compile");
        compile.setLoss("keras.losses.categorical_crossentropy");
        compile.setMetrics("'accuracy'");
        compile.setOptimizer("keras.optimizers.Adadelta()");
        Fit fit = new Fit();
        fit.setCurrent("fit");
        fit.setX("X_train");
        fit.setY("Y_train");
        fit.setBatchSize("50");
        fit.setEpochs("6");
        fit.setVerbose("1");
        Evaluate evaluate = new Evaluate();
        evaluate.setCurrent("evaluate");
        evaluate.setX("X_train");
        evaluate.setY("Y_train");
        evaluate.setVerbose("0");
        treeSet.add(conv2d1);
        treeSet.add(conv2d2);
        treeSet.add(maxPooling2D1);
        treeSet.add(conv2d3);
        treeSet.add(conv2d4);
        treeSet.add(maxPooling2D2);
        treeSet.add(conv2d5);
        treeSet.add(conv2d6);
        treeSet.add(conv2d7);
        treeSet.add(maxPooling2D3);
        treeSet.add(conv2d8);
        treeSet.add(conv2d9);
        treeSet.add(conv2d10);
        treeSet.add(maxPooling2D4);
        treeSet.add(conv2d11);
        treeSet.add(conv2d12);
        treeSet.add(conv2d13);
        treeSet.add(maxPooling2D5);
        treeSet.add(flatten);
        treeSet.add(dense1);
        treeSet.add(dropout1);
        treeSet.add(dense2);
        treeSet.add(dropout2);
        treeSet.add(dense3);
        treeSet.add(compile);
        treeSet.add(fit);
        treeSet.add(evaluate);
        return treeSet;
    }
}
