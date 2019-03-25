package com.rkb.dao;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-11-14 下午7:32
 */

public interface RandomDao {
    void addCore(String core);
    void deleteCore(int id);
    Integer findCore(String cores);
}
