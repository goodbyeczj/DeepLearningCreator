package com.rkb.bean.gson;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-7 下午4:31
 */

public class MobileNet {
    String include_top;
    String weights;
    String input_tensor;
    String input_shape;
    String pooling;
    String classes;

    public String getInclude_top() {
        return include_top;
    }

    public void setInclude_top(String include_top) {
        this.include_top = include_top;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getInput_tensor() {
        return input_tensor;
    }

    public void setInput_tensor(String input_tensor) {
        this.input_tensor = input_tensor;
    }

    public String getInput_shape() {
        return input_shape;
    }

    public void setInput_shape(String input_shape) {
        this.input_shape = input_shape;
    }

    public String getPooling() {
        return pooling;
    }

    public void setPooling(String pooling) {
        this.pooling = pooling;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
