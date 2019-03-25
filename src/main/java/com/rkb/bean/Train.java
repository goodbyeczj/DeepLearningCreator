package com.rkb.bean;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-18 下午7:08
 */
@Component
//@Scope("prototype")
public class Train {
    String LOSS = "";
    String ACC = "";
    String VAL_LOSS = "";
    String VAL_ACC = "";

    public String getLOSS() {
        return LOSS;
    }

    public void setLOSS(String LOSS) {
        this.LOSS = LOSS;
    }

    public String getACC() {
        return ACC;
    }

    public void setACC(String ACC) {
        this.ACC = ACC;
    }

    public String getVAL_LOSS() {
        return VAL_LOSS;
    }

    public void setVAL_LOSS(String VAL_LOSS) {
        this.VAL_LOSS = VAL_LOSS;
    }

    public String getVAL_ACC() {
        return VAL_ACC;
    }

    public void setVAL_ACC(String VAL_ACC) {
        this.VAL_ACC = VAL_ACC;
    }
}
