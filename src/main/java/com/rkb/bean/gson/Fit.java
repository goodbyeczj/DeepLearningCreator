package com.rkb.bean.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-9-26 上午11:18
 */

public class Fit {
    String in;
    String out;
    private String current;
    String x;
    String y;
    @SerializedName("batch_size")
    String batchSize;
    String epochs;
    String verbose;
    @SerializedName("validation_data")
    String validationData;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public String getEpochs() {
        return epochs;
    }

    public void setEpochs(String epochs) {
        this.epochs = epochs;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    public String getValidationData() {
        return validationData;
    }

    public void setValidationData(String validationData) {
        this.validationData = validationData;
    }
}
