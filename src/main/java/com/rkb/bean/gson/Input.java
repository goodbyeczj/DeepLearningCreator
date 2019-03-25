package com.rkb.bean.gson;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-10-14 下午2:36
 */

public class Input {
    private String out;
   
    private String current;
    private String shape;
    public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
