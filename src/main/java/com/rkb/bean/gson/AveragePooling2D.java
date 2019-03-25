package com.rkb.bean.gson;

import com.google.gson.annotations.SerializedName;

public class AveragePooling2D {
	
	private String in;
    private String out;
    private String current;
    @SerializedName("pool_size")
    String poolSize;
    @SerializedName("strides")
    String strides;
    @SerializedName("padding")
    String padding;
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
	public String getPoolSize() {
		return poolSize;
	}
	public void setPoolSize(String poolSize) {
		this.poolSize = poolSize;
	}
	public String getStrides() {
		return strides;
	}
	public void setStrides(String strides) {
		this.strides = strides;
	}
	public String getPadding() {
		return padding;
	}
	public void setPadding(String padding) {
		this.padding = padding;
	}
    
    
    
}
