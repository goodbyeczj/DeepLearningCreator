package com.rkb.bean.net.impl.fun;

import com.rkb.bean.gson.*;
import com.rkb.bean.net.Net;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-10-19 上午10:10
 */
@Component
public class GooLeNet<T> implements Net {
    public Set<Object> CreateNet(){
        Set<Object> hashSet = new HashSet<>();
        /**
         * 输入层
         */
        Input input = new Input();
        input.setCurrent("input");
        input.setOut("conv1_7x7_s2");
        input.setShape("(224,224,1)");
        /**
         * 卷积
         */
        Conv2D conv1_7x7_s2 = new Conv2D();
        conv1_7x7_s2.setCurrent("conv1_7x7_s2");
        conv1_7x7_s2.setIn("input");
        conv1_7x7_s2.setOut("maxpool1_3x3_s2");
        conv1_7x7_s2.setFilters("64");
        conv1_7x7_s2.setKernelSize("(7,7)");
        conv1_7x7_s2.setStrides("(2,2)");
        conv1_7x7_s2.setPadding("same");
        conv1_7x7_s2.setActivation("relu");
        conv1_7x7_s2.setKernel_regularizer("l2(0.01)");

        /**
         * 池化
         */
        MaxPooling2D maxpool1_3x3_s2 = new MaxPooling2D();
        maxpool1_3x3_s2.setCurrent("maxpool1_3x3_s2");
        maxpool1_3x3_s2.setIn("conv1_7x7_s2");
        maxpool1_3x3_s2.setOut("conv2_3x3_reduce");
        maxpool1_3x3_s2.setPoolSize("(3,3)");
        maxpool1_3x3_s2.setStrides("(2,2)");
        maxpool1_3x3_s2.setPadding("same");

        /**
         * 卷积
         */
        Conv2D conv2_3x3_reduce = new Conv2D();
        conv2_3x3_reduce.setCurrent("conv2_3x3_reduce");
        conv2_3x3_reduce.setIn("maxpool1_3x3_s2");
        conv2_3x3_reduce.setOut("conv2_3x3");
        conv2_3x3_reduce.setFilters("64");
        conv2_3x3_reduce.setKernelSize("(1,1)");
        conv2_3x3_reduce.setPadding("same");
        conv2_3x3_reduce.setActivation("relu");
        conv2_3x3_reduce.setKernel_regularizer("l2(0.01)");

        /**
         * 卷积
         */
        Conv2D conv2_3x3 = new Conv2D();
        conv2_3x3.setCurrent("conv2_3x3");
        conv2_3x3.setIn("conv2_3x3_reduce");
        conv2_3x3.setOut("maxpool2_3x3_s2");
        conv2_3x3.setFilters("192");
        conv2_3x3.setKernelSize("(3,3)");
        conv2_3x3.setActivation("relu");
        conv2_3x3.setKernel_regularizer("l2(0.01)");

        /**
         * 池化
         */
        MaxPooling2D maxpool2_3x3_s2 = new MaxPooling2D();
        maxpool2_3x3_s2.setCurrent("maxpool2_3x3_s2");
        maxpool2_3x3_s2.setIn("conv2_3x3");
        maxpool2_3x3_s2.setOut("c1_conv_1x1,c1_conv_3x3_reduce,c1_conv_5x5_reduce,c1_maxpool");
        maxpool2_3x3_s2.setPoolSize("(3,3)");
        maxpool2_3x3_s2.setStrides("(2,2)");
        maxpool2_3x3_s2.setPadding("same");

        /**
         * 循环1
         */
        Conv2D c1_conv_1x1 = new Conv2D();
        c1_conv_1x1.setIn("maxpool2_3x3_s2");
        c1_conv_1x1.setCurrent("c1_conv_1x1");
        c1_conv_1x1.setOut("c1_inception_output");
        c1_conv_1x1.setFilters("64");
        c1_conv_1x1.setKernelSize("(1,1)");
        c1_conv_1x1.setPadding("same");
        c1_conv_1x1.setActivation("relu");
        c1_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c1_conv_3x3_reduce = new Conv2D();
        c1_conv_3x3_reduce.setIn("maxpool2_3x3_s2");
        c1_conv_3x3_reduce.setCurrent("c1_conv_3x3_reduce");
        c1_conv_3x3_reduce.setOut("c1_conv_3x3");
        c1_conv_3x3_reduce.setFilters("96");
        c1_conv_3x3_reduce.setKernelSize("(1,1)");
        c1_conv_3x3_reduce.setPadding("same");
        c1_conv_3x3_reduce.setActivation("relu");
        c1_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c1_conv_3x3 = new Conv2D();
        c1_conv_3x3.setIn("c1_conv_3x3_reduce");
        c1_conv_3x3.setCurrent("c1_conv_3x3");
        c1_conv_3x3.setOut("c1_inception_output");
        c1_conv_3x3.setFilters("128");
        c1_conv_3x3.setKernelSize("(3,3)");
        c1_conv_3x3.setPadding("same");
        c1_conv_3x3.setActivation("relu");
        c1_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c1_conv_5x5_reduce = new Conv2D();
        c1_conv_5x5_reduce.setIn("maxpool2_3x3_s2");
        c1_conv_5x5_reduce.setCurrent("c1_conv_5x5_reduce");
        c1_conv_5x5_reduce.setOut("c1_conv_5x5");
        c1_conv_5x5_reduce.setFilters("16");
        c1_conv_5x5_reduce.setKernelSize("(1,1)");
        c1_conv_5x5_reduce.setPadding("same");
        c1_conv_5x5_reduce.setActivation("relu");
        c1_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c1_conv_5x5 = new Conv2D();
        c1_conv_5x5.setIn("c1_conv_5x5_reduce");
        c1_conv_5x5.setCurrent("c1_conv_5x5");
        c1_conv_5x5.setOut("c1_inception_output");
        c1_conv_5x5.setFilters("32");
        c1_conv_5x5.setKernelSize("(1,1)");
        c1_conv_5x5.setPadding("same");
        c1_conv_5x5.setActivation("relu");
        c1_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c1_maxpool = new MaxPooling2D();
        c1_maxpool.setIn("maxpool2_3x3_s2");
        c1_maxpool.setCurrent("c1_maxpool");
        c1_maxpool.setOut("c1_maxpool_proj");
        c1_maxpool.setPoolSize("(3,3)");
        c1_maxpool.setStrides("(1,1)");
        c1_maxpool.setPadding("same");


        Conv2D c1_maxpool_proj = new Conv2D();
        c1_maxpool_proj.setIn("c1_maxpool");
        c1_maxpool_proj.setCurrent("c1_maxpool_proj");
        c1_maxpool_proj.setOut("c1_inception_output");
        c1_maxpool_proj.setKernelSize("(1,1)");
        c1_maxpool_proj.setStrides("(1,1)");
        c1_maxpool_proj.setPadding("same");
        c1_maxpool_proj.setFilters("32");
        c1_maxpool_proj.setActivation("relu");
        c1_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c1_inception_output = new Concatenate();
        c1_inception_output.setIn("c1_conv_1x1,c1_conv_3x3,c1_conv_5x5,c1_maxpool_proj");
        c1_inception_output.setCurrent("c1_inception_output");
        c1_inception_output.setOut("c2_conv_1x1,c2_conv_3x3_reduce,c2_conv_5x5_reduce,c2_maxpool");
        c1_inception_output.setKwargs("[c1_conv_1x1,c1_conv_3x3,c1_conv_5x5,c1_maxpool_proj]");
        c1_inception_output.setAxis("3");



        /**
         * 循环2
         */
        Conv2D c2_conv_1x1 = new Conv2D();
        c2_conv_1x1.setIn("c1_inception_output");
        c2_conv_1x1.setCurrent("c2_conv_1x1");
        c2_conv_1x1.setOut("c2_inception_output");
        c2_conv_1x1.setFilters("128");
        c2_conv_1x1.setKernelSize("(1,1)");
        c2_conv_1x1.setPadding("same");
        c2_conv_1x1.setActivation("relu");
        c2_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c2_conv_3x3_reduce = new Conv2D();
        c2_conv_3x3_reduce.setIn("c1_inception_output");
        c2_conv_3x3_reduce.setCurrent("c2_conv_3x3_reduce");
        c2_conv_3x3_reduce.setOut("c2_conv_3x3");
        c2_conv_3x3_reduce.setFilters("128");
        c2_conv_3x3_reduce.setKernelSize("(1,1)");
        c2_conv_3x3_reduce.setPadding("same");
        c2_conv_3x3_reduce.setActivation("relu");
        c2_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c2_conv_3x3 = new Conv2D();
        c2_conv_3x3.setIn("c2_conv_3x3_reduce");
        c2_conv_3x3.setCurrent("c2_conv_3x3");
        c2_conv_3x3.setOut("c2_inception_output");
        c2_conv_3x3.setFilters("192");
        c2_conv_3x3.setKernelSize("(3,3)");
        c2_conv_3x3.setPadding("same");
        c2_conv_3x3.setActivation("relu");
        c2_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c2_conv_5x5_reduce = new Conv2D();
        c2_conv_5x5_reduce.setIn("c1_inception_output");
        c2_conv_5x5_reduce.setCurrent("c2_conv_5x5_reduce");
        c2_conv_5x5_reduce.setOut("c2_conv_5x5");
        c2_conv_5x5_reduce.setFilters("32");
        c2_conv_5x5_reduce.setKernelSize("(1,1)");
        c2_conv_5x5_reduce.setPadding("same");
        c2_conv_5x5_reduce.setActivation("relu");
        c2_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c2_conv_5x5 = new Conv2D();
        c2_conv_5x5.setIn("c2_conv_5x5_reduce");
        c2_conv_5x5.setCurrent("c2_conv_5x5");
        c2_conv_5x5.setOut("c2_inception_output");
        c2_conv_5x5.setFilters("96");
        c2_conv_5x5.setKernelSize("(1,1)");
        c2_conv_5x5.setPadding("same");
        c2_conv_5x5.setActivation("relu");
        c2_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c2_maxpool = new MaxPooling2D();
        c2_maxpool.setIn("c1_inception_output");
        c2_maxpool.setCurrent("c2_maxpool");
        c2_maxpool.setOut("c2_maxpool_proj");
        c2_maxpool.setPoolSize("(3,3)");
        c2_maxpool.setStrides("(1,1)");
        c2_maxpool.setPadding("same");


        Conv2D c2_maxpool_proj = new Conv2D();
        c2_maxpool_proj.setIn("c2_maxpool");
        c2_maxpool_proj.setCurrent("c2_maxpool_proj");
        c2_maxpool_proj.setOut("c2_inception_output");
        c2_maxpool_proj.setKernelSize("(1,1)");
        c2_maxpool_proj.setStrides("(1,1)");
        c2_maxpool_proj.setPadding("same");
        c2_maxpool_proj.setFilters("64");
        c2_maxpool_proj.setActivation("relu");
        c2_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c2_inception_output = new Concatenate();
        c2_inception_output.setIn("c2_conv_1x1,c2_conv_3x3,c2_conv_5x5,c2_maxpool_proj");
        c2_inception_output.setCurrent("c2_inception_output");
        c2_inception_output.setOut("maxpool3_3x3_s2");
        c2_inception_output.setKwargs("[c2_conv_1x1,c2_conv_3x3,c2_conv_5x5,c2_maxpool_proj]");
        c2_inception_output.setAxis("3");



        /**
         * 池化
         */
        MaxPooling2D maxpool3_3x3_s2 = new MaxPooling2D();
        maxpool3_3x3_s2.setIn("c2_inception_output");
        maxpool3_3x3_s2.setCurrent("maxpool3_3x3_s2");
        maxpool3_3x3_s2.setOut("c3_conv_1x1,c3_conv_3x3_reduce,c3_conv_5x5_reduce,c3_maxpool");
        maxpool3_3x3_s2.setPoolSize("(3,3)");
        maxpool3_3x3_s2.setStrides("(2,2)");
        maxpool3_3x3_s2.setPadding("same");






        /**
         * 循环3
         */
        Conv2D c3_conv_1x1 = new Conv2D();
        c3_conv_1x1.setIn("maxpool3_3x3_s2");
        c3_conv_1x1.setCurrent("c3_conv_1x1");
        c3_conv_1x1.setOut("c3_inception_output");
        c3_conv_1x1.setFilters("192");
        c3_conv_1x1.setKernelSize("(1,1)");
        c3_conv_1x1.setPadding("same");
        c3_conv_1x1.setActivation("relu");
        c3_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c3_conv_3x3_reduce = new Conv2D();
        c3_conv_3x3_reduce.setIn("maxpool3_3x3_s2");
        c3_conv_3x3_reduce.setCurrent("c3_conv_3x3_reduce");
        c3_conv_3x3_reduce.setOut("c3_conv_3x3");
        c3_conv_3x3_reduce.setFilters("96");
        c3_conv_3x3_reduce.setKernelSize("(1,1)");
        c3_conv_3x3_reduce.setPadding("same");
        c3_conv_3x3_reduce.setActivation("relu");
        c3_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c3_conv_3x3 = new Conv2D();
        c3_conv_3x3.setIn("c3_conv_3x3_reduce");
        c3_conv_3x3.setCurrent("c3_conv_3x3");
        c3_conv_3x3.setOut("c3_inception_output");
        c3_conv_3x3.setFilters("208");
        c3_conv_3x3.setKernelSize("(3,3)");
        c3_conv_3x3.setPadding("same");
        c3_conv_3x3.setActivation("relu");
        c3_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c3_conv_5x5_reduce = new Conv2D();
        c3_conv_5x5_reduce.setIn("maxpool3_3x3_s2");
        c3_conv_5x5_reduce.setCurrent("c3_conv_5x5_reduce");
        c3_conv_5x5_reduce.setOut("c3_conv_5x5");
        c3_conv_5x5_reduce.setFilters("16");
        c3_conv_5x5_reduce.setKernelSize("(1,1)");
        c3_conv_5x5_reduce.setPadding("same");
        c3_conv_5x5_reduce.setActivation("relu");
        c3_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c3_conv_5x5 = new Conv2D();
        c3_conv_5x5.setIn("c3_conv_5x5_reduce");
        c3_conv_5x5.setCurrent("c3_conv_5x5");
        c3_conv_5x5.setOut("c3_inception_output");
        c3_conv_5x5.setFilters("48");
        c3_conv_5x5.setKernelSize("(1,1)");
        c3_conv_5x5.setPadding("same");
        c3_conv_5x5.setActivation("relu");
        c3_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c3_maxpool = new MaxPooling2D();
        c3_maxpool.setIn("maxpool3_3x3_s2");
        c3_maxpool.setCurrent("c3_maxpool");
        c3_maxpool.setOut("c3_maxpool_proj");
        c3_maxpool.setPoolSize("(3,3)");
        c3_maxpool.setStrides("(1,1)");
        c3_maxpool.setPadding("same");


        Conv2D c3_maxpool_proj = new Conv2D();
        c3_maxpool_proj.setIn("c3_maxpool");
        c3_maxpool_proj.setCurrent("c3_maxpool_proj");
        c3_maxpool_proj.setOut("c3_inception_output");
        c3_maxpool_proj.setKernelSize("(1,1)");
        c3_maxpool_proj.setStrides("(1,1)");
        c3_maxpool_proj.setPadding("same");
        c3_maxpool_proj.setFilters("64");
        c3_maxpool_proj.setActivation("relu");
        c3_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c3_inception_output = new Concatenate();
        c3_inception_output.setIn("c3_conv_1x1,c3_conv_3x3,c3_conv_5x5,c3_maxpool_proj");
        c3_inception_output.setCurrent("c3_inception_output");
        c3_inception_output.setOut("c4_conv_1x1,c4_conv_3x3_reduce,c4_conv_5x5_reduce,c4_maxpool");
        c3_inception_output.setKwargs("[c3_conv_1x1,c3_conv_3x3,c3_conv_5x5,c3_maxpool_proj]");
        c3_inception_output.setAxis("3");



        /**
         * 循环4
         */
        Conv2D c4_conv_1x1 = new Conv2D();
        c4_conv_1x1.setIn("c3_inception_output");
        c4_conv_1x1.setCurrent("c4_conv_1x1");
        c4_conv_1x1.setOut("c4_inception_output");
        c4_conv_1x1.setFilters("160");
        c4_conv_1x1.setKernelSize("(1,1)");
        c4_conv_1x1.setPadding("same");
        c4_conv_1x1.setActivation("relu");
        c4_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c4_conv_3x3_reduce = new Conv2D();
        c4_conv_3x3_reduce.setIn("c3_inception_output");
        c4_conv_3x3_reduce.setCurrent("c4_conv_3x3_reduce");
        c4_conv_3x3_reduce.setOut("c4_conv_3x3");
        c4_conv_3x3_reduce.setFilters("112");
        c4_conv_3x3_reduce.setKernelSize("(1,1)");
        c4_conv_3x3_reduce.setPadding("same");
        c4_conv_3x3_reduce.setActivation("relu");
        c4_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c4_conv_3x3 = new Conv2D();
        c4_conv_3x3.setIn("c4_conv_3x3_reduce");
        c4_conv_3x3.setCurrent("c4_conv_3x3");
        c4_conv_3x3.setOut("c4_inception_output");
        c4_conv_3x3.setFilters("224");
        c4_conv_3x3.setKernelSize("(3,3)");
        c4_conv_3x3.setPadding("same");
        c4_conv_3x3.setActivation("relu");
        c4_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c4_conv_5x5_reduce = new Conv2D();
        c4_conv_5x5_reduce.setIn("c3_inception_output");
        c4_conv_5x5_reduce.setCurrent("c4_conv_5x5_reduce");
        c4_conv_5x5_reduce.setOut("c4_conv_5x5");
        c4_conv_5x5_reduce.setFilters("24");
        c4_conv_5x5_reduce.setKernelSize("(1,1)");
        c4_conv_5x5_reduce.setPadding("same");
        c4_conv_5x5_reduce.setActivation("relu");
        c4_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c4_conv_5x5 = new Conv2D();
        c4_conv_5x5.setIn("c4_conv_5x5_reduce");
        c4_conv_5x5.setCurrent("c4_conv_5x5");
        c4_conv_5x5.setOut("c4_inception_output");
        c4_conv_5x5.setFilters("64");
        c4_conv_5x5.setKernelSize("(1,1)");
        c4_conv_5x5.setPadding("same");
        c4_conv_5x5.setActivation("relu");
        c4_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c4_maxpool = new MaxPooling2D();
        c4_maxpool.setIn("c3_inception_output");
        c4_maxpool.setCurrent("c4_maxpool");
        c4_maxpool.setOut("c4_maxpool_proj");
        c4_maxpool.setPoolSize("(3,3)");
        c4_maxpool.setStrides("(1,1)");
        c4_maxpool.setPadding("same");


        Conv2D c4_maxpool_proj = new Conv2D();
        c4_maxpool_proj.setIn("c4_maxpool");
        c4_maxpool_proj.setCurrent("c4_maxpool_proj");
        c4_maxpool_proj.setOut("c4_inception_output");
        c4_maxpool_proj.setKernelSize("(1,1)");
        c4_maxpool_proj.setStrides("(1,1)");
        c4_maxpool_proj.setPadding("same");
        c4_maxpool_proj.setFilters("64");
        c4_maxpool_proj.setActivation("relu");
        c4_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c4_inception_output = new Concatenate();
        c4_inception_output.setIn("c4_conv_1x1,c4_conv_3x3,c4_conv_5x5,c4_maxpool_proj");
        c4_inception_output.setCurrent("c4_inception_output");
        c4_inception_output.setOut("c5_conv_1x1,c5_conv_3x3_reduce,c5_conv_5x5_reduce,c5_maxpool");
        c4_inception_output.setKwargs("[c4_conv_1x1,c4_conv_3x3,c4_conv_5x5,c4_maxpool_proj]");
        c4_inception_output.setAxis("3");



        /**
         * 循环5
         */
        Conv2D c5_conv_1x1 = new Conv2D();
        c5_conv_1x1.setIn("c4_inception_output");
        c5_conv_1x1.setCurrent("c5_conv_1x1");
        c5_conv_1x1.setOut("c5_inception_output");
        c5_conv_1x1.setFilters("128");
        c5_conv_1x1.setKernelSize("(1,1)");
        c5_conv_1x1.setPadding("same");
        c5_conv_1x1.setActivation("relu");
        c5_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c5_conv_3x3_reduce = new Conv2D();
        c5_conv_3x3_reduce.setIn("c4_inception_output");
        c5_conv_3x3_reduce.setCurrent("c5_conv_3x3_reduce");
        c5_conv_3x3_reduce.setOut("c5_conv_3x3");
        c5_conv_3x3_reduce.setFilters("128");
        c5_conv_3x3_reduce.setKernelSize("(1,1)");
        c5_conv_3x3_reduce.setPadding("same");
        c5_conv_3x3_reduce.setActivation("relu");
        c5_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c5_conv_3x3 = new Conv2D();
        c5_conv_3x3.setIn("c5_conv_3x3_reduce");
        c5_conv_3x3.setCurrent("c5_conv_3x3");
        c5_conv_3x3.setOut("c5_inception_output");
        c5_conv_3x3.setFilters("256");
        c5_conv_3x3.setKernelSize("(3,3)");
        c5_conv_3x3.setPadding("same");
        c5_conv_3x3.setActivation("relu");
        c5_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c5_conv_5x5_reduce = new Conv2D();
        c5_conv_5x5_reduce.setIn("c4_inception_output");
        c5_conv_5x5_reduce.setCurrent("c5_conv_5x5_reduce");
        c5_conv_5x5_reduce.setOut("c5_conv_5x5");
        c5_conv_5x5_reduce.setFilters("24");
        c5_conv_5x5_reduce.setKernelSize("(1,1)");
        c5_conv_5x5_reduce.setPadding("same");
        c5_conv_5x5_reduce.setActivation("relu");
        c5_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c5_conv_5x5 = new Conv2D();
        c5_conv_5x5.setIn("c5_conv_5x5_reduce");
        c5_conv_5x5.setCurrent("c5_conv_5x5");
        c5_conv_5x5.setOut("c5_inception_output");
        c5_conv_5x5.setFilters("64");
        c5_conv_5x5.setKernelSize("(1,1)");
        c5_conv_5x5.setPadding("same");
        c5_conv_5x5.setActivation("relu");
        c5_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c5_maxpool = new MaxPooling2D();
        c5_maxpool.setIn("c4_inception_output");
        c5_maxpool.setCurrent("c5_maxpool");
        c5_maxpool.setOut("c5_maxpool_proj");
        c5_maxpool.setPoolSize("(3,3)");
        c5_maxpool.setStrides("(1,1)");
        c5_maxpool.setPadding("same");


        Conv2D c5_maxpool_proj = new Conv2D();
        c5_maxpool_proj.setIn("c5_maxpool");
        c5_maxpool_proj.setCurrent("c5_maxpool_proj");
        c5_maxpool_proj.setOut("c5_inception_output");
        c5_maxpool_proj.setKernelSize("(1,1)");
        c5_maxpool_proj.setStrides("(1,1)");
        c5_maxpool_proj.setPadding("same");
        c5_maxpool_proj.setFilters("64");
        c5_maxpool_proj.setActivation("relu");
        c5_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c5_inception_output = new Concatenate();
        c5_inception_output.setIn("c5_conv_1x1,c5_conv_3x3,c5_conv_5x5,c5_maxpool_proj");
        c5_inception_output.setCurrent("c5_inception_output");
        c5_inception_output.setOut("c6_conv_1x1,c6_conv_3x3_reduce,c6_conv_5x5_reduce,c6_maxpool");
        c5_inception_output.setKwargs("[c5_conv_1x1,c5_conv_3x3,c5_conv_5x5,c5_maxpool_proj]");
        c5_inception_output.setAxis("3");



        /**
         * 循环6
         */
        Conv2D c6_conv_1x1 = new Conv2D();
        c6_conv_1x1.setIn("c5_inception_output");
        c6_conv_1x1.setCurrent("c6_conv_1x1");
        c6_conv_1x1.setOut("c6_inception_output");
        c6_conv_1x1.setFilters("112");
        c6_conv_1x1.setKernelSize("(1,1)");
        c6_conv_1x1.setPadding("same");
        c6_conv_1x1.setActivation("relu");
        c6_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c6_conv_3x3_reduce = new Conv2D();
        c6_conv_3x3_reduce.setIn("c5_inception_output");
        c6_conv_3x3_reduce.setCurrent("c6_conv_3x3_reduce");
        c6_conv_3x3_reduce.setOut("c6_conv_3x3");
        c6_conv_3x3_reduce.setFilters("144");
        c6_conv_3x3_reduce.setKernelSize("(1,1)");
        c6_conv_3x3_reduce.setPadding("same");
        c6_conv_3x3_reduce.setActivation("relu");
        c6_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c6_conv_3x3 = new Conv2D();
        c6_conv_3x3.setIn("c6_conv_3x3_reduce");
        c6_conv_3x3.setCurrent("c6_conv_3x3");
        c6_conv_3x3.setOut("c6_inception_output");
        c6_conv_3x3.setFilters("288");
        c6_conv_3x3.setKernelSize("(3,3)");
        c6_conv_3x3.setPadding("same");
        c6_conv_3x3.setActivation("relu");
        c6_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c6_conv_5x5_reduce = new Conv2D();
        c6_conv_5x5_reduce.setIn("c5_inception_output");
        c6_conv_5x5_reduce.setCurrent("c6_conv_5x5_reduce");
        c6_conv_5x5_reduce.setOut("c6_conv_5x5");
        c6_conv_5x5_reduce.setFilters("32");
        c6_conv_5x5_reduce.setKernelSize("(1,1)");
        c6_conv_5x5_reduce.setPadding("same");
        c6_conv_5x5_reduce.setActivation("relu");
        c6_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c6_conv_5x5 = new Conv2D();
        c6_conv_5x5.setIn("c6_conv_5x5_reduce");
        c6_conv_5x5.setCurrent("c6_conv_5x5");
        c6_conv_5x5.setOut("c6_inception_output");
        c6_conv_5x5.setFilters("64");
        c6_conv_5x5.setKernelSize("(1,1)");
        c6_conv_5x5.setPadding("same");
        c6_conv_5x5.setActivation("relu");
        c6_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c6_maxpool = new MaxPooling2D();
        c6_maxpool.setIn("c5_inception_output");
        c6_maxpool.setCurrent("c6_maxpool");
        c6_maxpool.setOut("c6_maxpool_proj");
        c6_maxpool.setPoolSize("(3,3)");
        c6_maxpool.setStrides("(1,1)");
        c6_maxpool.setPadding("same");


        Conv2D c6_maxpool_proj = new Conv2D();
        c6_maxpool_proj.setIn("c6_maxpool");
        c6_maxpool_proj.setCurrent("c6_maxpool_proj");
        c6_maxpool_proj.setOut("c6_inception_output");
        c6_maxpool_proj.setKernelSize("(1,1)");
        c6_maxpool_proj.setStrides("(1,1)");
        c6_maxpool_proj.setPadding("same");
        c6_maxpool_proj.setFilters("64");
        c6_maxpool_proj.setActivation("relu");
        c6_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c6_inception_output = new Concatenate();
        c6_inception_output.setIn("c6_conv_1x1,c6_conv_3x3,c6_conv_5x5,c6_maxpool_proj");
        c6_inception_output.setCurrent("c6_inception_output");
        c6_inception_output.setOut("c7_conv_1x1,c7_conv_3x3_reduce,c7_conv_5x5_reduce,c7_maxpool");
        c6_inception_output.setKwargs("[c6_conv_1x1,c6_conv_3x3,c6_conv_5x5,c6_maxpool_proj]");
        c6_inception_output.setAxis("3");




        /**
         * 循环7
         */
        Conv2D c7_conv_1x1 = new Conv2D();
        c7_conv_1x1.setIn("c6_inception_output");
        c7_conv_1x1.setCurrent("c7_conv_1x1");
        c7_conv_1x1.setOut("c7_inception_output");
        c7_conv_1x1.setFilters("256");
        c7_conv_1x1.setKernelSize("(1,1)");
        c7_conv_1x1.setPadding("same");
        c7_conv_1x1.setActivation("relu");
        c7_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c7_conv_3x3_reduce = new Conv2D();
        c7_conv_3x3_reduce.setIn("c6_inception_output");
        c7_conv_3x3_reduce.setCurrent("c7_conv_3x3_reduce");
        c7_conv_3x3_reduce.setOut("c7_conv_3x3");
        c7_conv_3x3_reduce.setFilters("160");
        c7_conv_3x3_reduce.setKernelSize("(1,1)");
        c7_conv_3x3_reduce.setPadding("same");
        c7_conv_3x3_reduce.setActivation("relu");
        c7_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c7_conv_3x3 = new Conv2D();
        c7_conv_3x3.setIn("c7_conv_3x3_reduce");
        c7_conv_3x3.setCurrent("c7_conv_3x3");
        c7_conv_3x3.setOut("c7_inception_output");
        c7_conv_3x3.setFilters("320");
        c7_conv_3x3.setKernelSize("(3,3)");
        c7_conv_3x3.setPadding("same");
        c7_conv_3x3.setActivation("relu");
        c7_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c7_conv_5x5_reduce = new Conv2D();
        c7_conv_5x5_reduce.setIn("c6_inception_output");
        c7_conv_5x5_reduce.setCurrent("c7_conv_5x5_reduce");
        c7_conv_5x5_reduce.setOut("c7_conv_5x5");
        c7_conv_5x5_reduce.setFilters("32");
        c7_conv_5x5_reduce.setKernelSize("(1,1)");
        c7_conv_5x5_reduce.setPadding("same");
        c7_conv_5x5_reduce.setActivation("relu");
        c7_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c7_conv_5x5 = new Conv2D();
        c7_conv_5x5.setIn("c7_conv_5x5_reduce");
        c7_conv_5x5.setCurrent("c7_conv_5x5");
        c7_conv_5x5.setOut("c7_inception_output");
        c7_conv_5x5.setFilters("128");
        c7_conv_5x5.setKernelSize("(1,1)");
        c7_conv_5x5.setPadding("same");
        c7_conv_5x5.setActivation("relu");
        c7_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c7_maxpool = new MaxPooling2D();
        c7_maxpool.setIn("c6_inception_output");
        c7_maxpool.setCurrent("c7_maxpool");
        c7_maxpool.setOut("c7_maxpool_proj");
        c7_maxpool.setPoolSize("(3,3)");
        c7_maxpool.setStrides("(1,1)");
        c7_maxpool.setPadding("same");


        Conv2D c7_maxpool_proj = new Conv2D();
        c7_maxpool_proj.setIn("c7_maxpool");
        c7_maxpool_proj.setCurrent("c7_maxpool_proj");
        c7_maxpool_proj.setOut("c7_inception_output");
        c7_maxpool_proj.setKernelSize("(1,1)");
        c7_maxpool_proj.setStrides("(1,1)");
        c7_maxpool_proj.setPadding("same");
        c7_maxpool_proj.setFilters("128");
        c7_maxpool_proj.setActivation("relu");
        c7_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c7_inception_output = new Concatenate();
        c7_inception_output.setIn("c7_conv_1x1,c7_conv_3x3,c7_conv_5x5,c7_maxpool_proj");
        c7_inception_output.setCurrent("c7_inception_output");
        c7_inception_output.setOut("maxpool4_3x3_s2");
        c7_inception_output.setKwargs("[c7_conv_1x1,c7_conv_3x3,c7_conv_5x5,c7_maxpool_proj]");
        c7_inception_output.setAxis("3");



        MaxPooling2D maxpool4_3x3_s2 = new MaxPooling2D();
        maxpool4_3x3_s2.setIn("c7_inception_output");
        maxpool4_3x3_s2.setCurrent("maxpool4_3x3_s2");
        maxpool4_3x3_s2.setOut("c8_conv_1x1,c8_conv_3x3_reduce,c8_conv_5x5_reduce,c8_maxpool");
        maxpool4_3x3_s2.setPoolSize("(3,3)");
        maxpool4_3x3_s2.setStrides("(2,2)");
        maxpool4_3x3_s2.setPadding("same");






        /**
         * 循环8
         */
        Conv2D c8_conv_1x1 = new Conv2D();
        c8_conv_1x1.setIn("maxpool4_3x3_s2");
        c8_conv_1x1.setCurrent("c8_conv_1x1");
        c8_conv_1x1.setOut("c8_inception_output");
        c8_conv_1x1.setFilters("256");
        c8_conv_1x1.setKernelSize("(1,1)");
        c8_conv_1x1.setPadding("same");
        c8_conv_1x1.setActivation("relu");
        c8_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c8_conv_3x3_reduce = new Conv2D();
        c8_conv_3x3_reduce.setIn("maxpool4_3x3_s2");
        c8_conv_3x3_reduce.setCurrent("c8_conv_3x3_reduce");
        c8_conv_3x3_reduce.setOut("c8_conv_3x3");
        c8_conv_3x3_reduce.setFilters("160");
        c8_conv_3x3_reduce.setKernelSize("(1,1)");
        c8_conv_3x3_reduce.setPadding("same");
        c8_conv_3x3_reduce.setActivation("relu");
        c8_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c8_conv_3x3 = new Conv2D();
        c8_conv_3x3.setIn("c8_conv_3x3_reduce");
        c8_conv_3x3.setCurrent("c8_conv_3x3");
        c8_conv_3x3.setOut("c8_inception_output");
        c8_conv_3x3.setFilters("320");
        c8_conv_3x3.setKernelSize("(3,3)");
        c8_conv_3x3.setPadding("same");
        c8_conv_3x3.setActivation("relu");
        c8_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c8_conv_5x5_reduce = new Conv2D();
        c8_conv_5x5_reduce.setIn("maxpool4_3x3_s2");
        c8_conv_5x5_reduce.setCurrent("c8_conv_5x5_reduce");
        c8_conv_5x5_reduce.setOut("c8_conv_5x5");
        c8_conv_5x5_reduce.setFilters("32");
        c8_conv_5x5_reduce.setKernelSize("(1,1)");
        c8_conv_5x5_reduce.setPadding("same");
        c8_conv_5x5_reduce.setActivation("relu");
        c8_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c8_conv_5x5 = new Conv2D();
        c8_conv_5x5.setIn("c8_conv_5x5_reduce");
        c8_conv_5x5.setCurrent("c8_conv_5x5");
        c8_conv_5x5.setOut("c8_inception_output");
        c8_conv_5x5.setFilters("128");
        c8_conv_5x5.setKernelSize("(1,1)");
        c8_conv_5x5.setPadding("same");
        c8_conv_5x5.setActivation("relu");
        c8_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c8_maxpool = new MaxPooling2D();
        c8_maxpool.setIn("maxpool4_3x3_s2");
        c8_maxpool.setCurrent("c8_maxpool");
        c8_maxpool.setOut("c8_maxpool_proj");
        c8_maxpool.setPoolSize("(3,3)");
        c8_maxpool.setStrides("(1,1)");
        c8_maxpool.setPadding("same");


        Conv2D c8_maxpool_proj = new Conv2D();
        c8_maxpool_proj.setIn("c8_maxpool");
        c8_maxpool_proj.setCurrent("c8_maxpool_proj");
        c8_maxpool_proj.setOut("c8_inception_output");
        c8_maxpool_proj.setKernelSize("(1,1)");
        c8_maxpool_proj.setStrides("(1,1)");
        c8_maxpool_proj.setPadding("same");
        c8_maxpool_proj.setFilters("128");
        c8_maxpool_proj.setActivation("relu");
        c8_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c8_inception_output = new Concatenate();
        c8_inception_output.setIn("c8_conv_1x1,c8_conv_3x3,c8_conv_5x5,c8_maxpool_proj");
        c8_inception_output.setCurrent("c8_inception_output");
        c8_inception_output.setOut("c9_conv_1x1,c9_conv_3x3_reduce,c9_conv_5x5_reduce,c9_maxpool");
        c8_inception_output.setKwargs("[c8_conv_1x1,c8_conv_3x3,c8_conv_5x5,c8_maxpool_proj]");
        c8_inception_output.setAxis("3");


        /**
         * 循环9
         */
        Conv2D c9_conv_1x1 = new Conv2D();
        c9_conv_1x1.setIn("c8_inception_output");
        c9_conv_1x1.setCurrent("c9_conv_1x1");
        c9_conv_1x1.setOut("c9_inception_output");
        c9_conv_1x1.setFilters("384");
        c9_conv_1x1.setKernelSize("(1,1)");
        c9_conv_1x1.setPadding("same");
        c9_conv_1x1.setActivation("relu");
        c9_conv_1x1.setKernel_regularizer("l2(0.01)");


        Conv2D c9_conv_3x3_reduce = new Conv2D();
        c9_conv_3x3_reduce.setIn("c8_inception_output");
        c9_conv_3x3_reduce.setCurrent("c9_conv_3x3_reduce");
        c9_conv_3x3_reduce.setOut("c9_conv_3x3");
        c9_conv_3x3_reduce.setFilters("192");
        c9_conv_3x3_reduce.setKernelSize("(1,1)");
        c9_conv_3x3_reduce.setPadding("same");
        c9_conv_3x3_reduce.setActivation("relu");
        c9_conv_3x3_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c9_conv_3x3 = new Conv2D();
        c9_conv_3x3.setIn("c9_conv_3x3_reduce");
        c9_conv_3x3.setCurrent("c9_conv_3x3");
        c9_conv_3x3.setOut("c9_inception_output");
        c9_conv_3x3.setFilters("384");
        c9_conv_3x3.setKernelSize("(3,3)");
        c9_conv_3x3.setPadding("same");
        c9_conv_3x3.setActivation("relu");
        c9_conv_3x3.setKernel_regularizer("l2(0.01)");


        Conv2D c9_conv_5x5_reduce = new Conv2D();
        c9_conv_5x5_reduce.setIn("c8_inception_output");
        c9_conv_5x5_reduce.setCurrent("c9_conv_5x5_reduce");
        c9_conv_5x5_reduce.setOut("c9_conv_5x5");
        c9_conv_5x5_reduce.setFilters("48");
        c9_conv_5x5_reduce.setKernelSize("(1,1)");
        c9_conv_5x5_reduce.setPadding("same");
        c9_conv_5x5_reduce.setActivation("relu");
        c9_conv_5x5_reduce.setKernel_regularizer("l2(0.01)");


        Conv2D c9_conv_5x5 = new Conv2D();
        c9_conv_5x5.setIn("c9_conv_5x5_reduce");
        c9_conv_5x5.setCurrent("c9_conv_5x5");
        c9_conv_5x5.setOut("c9_inception_output");
        c9_conv_5x5.setFilters("128");
        c9_conv_5x5.setKernelSize("(1,1)");
        c9_conv_5x5.setPadding("same");
        c9_conv_5x5.setActivation("relu");
        c9_conv_5x5.setKernel_regularizer("l2(0.01)");


        MaxPooling2D c9_maxpool = new MaxPooling2D();
        c9_maxpool.setIn("c8_inception_output");
        c9_maxpool.setCurrent("c9_maxpool");
        c9_maxpool.setOut("c9_maxpool_proj");
        c9_maxpool.setPoolSize("(3,3)");
        c9_maxpool.setStrides("(1,1)");
        c9_maxpool.setPadding("same");


        Conv2D c9_maxpool_proj = new Conv2D();
        c9_maxpool_proj.setIn("c9_maxpool");
        c9_maxpool_proj.setCurrent("c9_maxpool_proj");
        c9_maxpool_proj.setOut("c9_inception_output");
        c9_maxpool_proj.setKernelSize("(1,1)");
        c9_maxpool_proj.setStrides("(1,1)");
        c9_maxpool_proj.setPadding("same");
        c9_maxpool_proj.setFilters("128");
        c9_maxpool_proj.setActivation("relu");
        c9_maxpool_proj.setKernel_regularizer("l2(0.01)");


        Concatenate c9_inception_output = new Concatenate();
        c9_inception_output.setIn("c9_conv_1x1,c9_conv_3x3,c9_conv_5x5,c9_maxpool_proj");
        c9_inception_output.setCurrent("c9_inception_output");
        c9_inception_output.setOut("averagepool1_7x7_s1");
        c9_inception_output.setKwargs("[c9_conv_1x1,c9_conv_3x3,c9_conv_5x5,c9_maxpool_proj]");
        c9_inception_output.setAxis("3");


        /**
         * 池化
         */
        AveragePooling2D averagepool1_7x7_s1 = new AveragePooling2D();
        averagepool1_7x7_s1.setIn("c9_inception_output");
        averagepool1_7x7_s1.setCurrent("averagepool1_7x7_s1");
        averagepool1_7x7_s1.setOut("drop1");
        averagepool1_7x7_s1.setPoolSize("(7,7)");
        averagepool1_7x7_s1.setStrides("(7,7)");
        averagepool1_7x7_s1.setPadding("same");


        /**
         * drop
         */
        Dropout drop1 = new Dropout();
        drop1.setIn("averagepool1_7x7_s1");
        drop1.setCurrent("drop1");
        drop1.setOut("flatten");
        drop1.setRate("0.4");

        Flatten flatten = new Flatten();
        flatten.setCurrent("flatten");
        flatten.setIn("drop1");
        flatten.setOut("linear");
        /**
         * 卷积
         */
        Dense linear = new Dense();
        linear.setIn("flatten");
        linear.setCurrent("linear");
        linear.setUnits("10");
        linear.setActivation("softmax");
        linear.setKernelRegularizer("l2(0.01)");



        Model model = new Model();
        model.setInputs("input");
        model.setOutputs("linear");
        model.setCurrent("model");
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

        hashSet.add(input);
        hashSet.add(conv1_7x7_s2);
        hashSet.add(maxpool1_3x3_s2);
        hashSet.add(conv2_3x3_reduce);
        hashSet.add(conv2_3x3);
        hashSet.add(maxpool2_3x3_s2);
        hashSet.add(maxpool3_3x3_s2);
        hashSet.add(maxpool4_3x3_s2);
        hashSet.add(averagepool1_7x7_s1);
        hashSet.add(drop1);
        hashSet.add(linear);
        hashSet.add(flatten);



        hashSet.add(c1_conv_1x1);
        hashSet.add(c1_conv_3x3_reduce);
        hashSet.add(c1_conv_3x3);
        hashSet.add(c1_conv_5x5_reduce);
        hashSet.add(c1_conv_5x5);
        hashSet.add(c1_maxpool);
        hashSet.add(c1_maxpool_proj);
        hashSet.add(c1_inception_output);



        hashSet.add(c2_conv_1x1);
        hashSet.add(c2_conv_3x3_reduce);
        hashSet.add(c2_conv_3x3);
        hashSet.add(c2_conv_5x5_reduce);
        hashSet.add(c2_conv_5x5);
        hashSet.add(c2_maxpool);
        hashSet.add(c2_maxpool_proj);
        hashSet.add(c2_inception_output);


        hashSet.add(c3_conv_1x1);
        hashSet.add(c3_conv_3x3_reduce);
        hashSet.add(c3_conv_3x3);
        hashSet.add(c3_conv_5x5_reduce);
        hashSet.add(c3_conv_5x5);
        hashSet.add(c3_maxpool);
        hashSet.add(c3_maxpool_proj);
        hashSet.add(c3_inception_output);


        hashSet.add(c4_conv_1x1);
        hashSet.add(c4_conv_3x3_reduce);
        hashSet.add(c4_conv_3x3);
        hashSet.add(c4_conv_5x5_reduce);
        hashSet.add(c4_conv_5x5);
        hashSet.add(c4_maxpool);
        hashSet.add(c4_maxpool_proj);
        hashSet.add(c4_inception_output);


        hashSet.add(c5_conv_1x1);
        hashSet.add(c5_conv_3x3_reduce);
        hashSet.add(c5_conv_3x3);
        hashSet.add(c5_conv_5x5_reduce);
        hashSet.add(c5_conv_5x5);
        hashSet.add(c5_maxpool);
        hashSet.add(c5_maxpool_proj);
        hashSet.add(c5_inception_output);


        hashSet.add(c6_conv_1x1);
        hashSet.add(c6_conv_3x3_reduce);
        hashSet.add(c6_conv_3x3);
        hashSet.add(c6_conv_5x5_reduce);
        hashSet.add(c6_conv_5x5);
        hashSet.add(c6_maxpool);
        hashSet.add(c6_maxpool_proj);
        hashSet.add(c6_inception_output);


        hashSet.add(c7_conv_1x1);
        hashSet.add(c7_conv_3x3_reduce);
        hashSet.add(c7_conv_3x3);
        hashSet.add(c7_conv_5x5_reduce);
        hashSet.add(c7_conv_5x5);
        hashSet.add(c7_maxpool);
        hashSet.add(c7_maxpool_proj);
        hashSet.add(c7_inception_output);


        hashSet.add(c8_conv_1x1);
        hashSet.add(c8_conv_3x3_reduce);
        hashSet.add(c8_conv_3x3);
        hashSet.add(c8_conv_5x5_reduce);
        hashSet.add(c8_conv_5x5);
        hashSet.add(c8_maxpool);
        hashSet.add(c8_maxpool_proj);
        hashSet.add(c8_inception_output);


        hashSet.add(c9_conv_1x1);
        hashSet.add(c9_conv_3x3_reduce);
        hashSet.add(c9_conv_3x3);
        hashSet.add(c9_conv_5x5_reduce);
        hashSet.add(c9_conv_5x5);
        hashSet.add(c9_maxpool);
        hashSet.add(c9_maxpool_proj);
        hashSet.add(c9_inception_output);
        hashSet.add(model);
        hashSet.add(compile);
        hashSet.add(fit);
        hashSet.add(evaluate);
        return hashSet;

    }
}
