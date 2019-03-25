package com.rkb.bean.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-9-27 下午3:49
 */

public class Conv2D {
    private String in;
    private String out;
    private String current;
    String filters;
    @SerializedName("kernel_size")
    String kernelSize;
    String strides;
    @SerializedName("border_mode")
    String borderMode;
    @SerializedName("input_shape")
    String inputShape;
    @SerializedName("padding")
    String padding;
    @SerializedName("activation")
    String activation;
    @SerializedName("kernel_regularizer")
    String kernel_regularizer;
    @SerializedName("data_format")
    String data_format;

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getKernelSize() {
        return kernelSize;
    }

    public void setKernelSize(String kernelSize) {
        this.kernelSize = kernelSize;
    }

    public String getStrides() {
        return strides;
    }

    public void setStrides(String strides) {
        this.strides = strides;
    }

    public String getBorderMode() {
        return borderMode;
    }

    public void setBorderMode(String borderMode) {
        this.borderMode = borderMode;
    }

    public String getInputShape() {
        return inputShape;
    }

    public void setInputShape(String inputShape) {
        this.inputShape = inputShape;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getKernel_regularizer() {
        return kernel_regularizer;
    }

    public void setKernel_regularizer(String kernel_regularizer) {
        this.kernel_regularizer = kernel_regularizer;
    }

    public String getData_format() {
        return data_format;
    }

    public void setData_format(String data_format) {
        this.data_format = data_format;
    }
}
