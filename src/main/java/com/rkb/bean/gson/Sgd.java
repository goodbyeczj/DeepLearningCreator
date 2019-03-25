package com.rkb.bean.gson;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-9-27 上午10:04
 */

public class Sgd {
    private String in;
    private String out;
    private String lr;
    private String momentum;
    private String decay;
    private String nesterov;
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
    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }

    public String getMomentum() {
        return momentum;
    }

    public void setMomentum(String momentum) {
        this.momentum = momentum;
    }

    public String getDecay() {
        return decay;
    }

    public void setDecay(String decay) {
        this.decay = decay;
    }

    public String getNesterov() {
        return nesterov;
    }

    public void setNesterov(String nesterov) {
        this.nesterov = nesterov;
    }
}
