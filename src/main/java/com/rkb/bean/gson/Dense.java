package com.rkb.bean.gson;

import com.google.gson.annotations.SerializedName;

public class Dense {
    private String in;
   
    private String out;
    private String current;
    private String units;
    private String activation;
    @SerializedName("use_bias")
    private String useBias;
    @SerializedName("kernel_initializer")
    private String kernelInitializer;
    @SerializedName("bias_initializer")
    private String biasInitializer;
    @SerializedName("kernel_regularizer")
    private String kernelRegularizer;
    @SerializedName("bias_regularizer")
    private String biasRegularizer;
    @SerializedName("activity_regularizer")
    private String activityRegularizer;
    @SerializedName("kernel_constraint")
    private String kernelConstraint;
    @SerializedName("bias_constraints")
    private String biasConstraints;
    
    
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }


    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getUseBias() {
        return useBias;
    }

    public void setUseBias(String useBias) {
        this.useBias = useBias;
    }

    public String getKernelInitializer() {
        return kernelInitializer;
    }

    public void setKernelInitializer(String kernelInitializer) {
        this.kernelInitializer = kernelInitializer;
    }

    public String getBiasInitializer() {
        return biasInitializer;
    }

    public void setBiasInitializer(String biasInitializer) {
        this.biasInitializer = biasInitializer;
    }

    public String getKernelRegularizer() {
        return kernelRegularizer;
    }

    public void setKernelRegularizer(String kernelRegularizer) {
        this.kernelRegularizer = kernelRegularizer;
    }

    public String getBiasRegularizer() {
        return biasRegularizer;
    }

    public void setBiasRegularizer(String biasRegularizer) {
        this.biasRegularizer = biasRegularizer;
    }

    public String getActivityRegularizer() {
        return activityRegularizer;
    }

    public void setActivityRegularizer(String activityRegularizer) {
        this.activityRegularizer = activityRegularizer;
    }

    public String getKernelConstraint() {
        return kernelConstraint;
    }

    public void setKernelConstraint(String kernelConstraint) {
        this.kernelConstraint = kernelConstraint;
    }

    public String getBiasConstraints() {
        return biasConstraints;
    }

    public void setBiasConstraints(String biasConstraints) {
        this.biasConstraints = biasConstraints;
    }
}
