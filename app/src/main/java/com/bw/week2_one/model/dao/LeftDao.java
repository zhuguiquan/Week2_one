package com.bw.week2_one.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
@Entity
public class LeftDao {
    private String leftDaoJson;

    @Generated(hash = 1062818594)
    public LeftDao(String leftDaoJson) {
        this.leftDaoJson = leftDaoJson;
    }

    @Generated(hash = 789308008)
    public LeftDao() {
    }

    public String getLeftDaoJson() {
        return this.leftDaoJson;
    }

    public void setLeftDaoJson(String leftDaoJson) {
        this.leftDaoJson = leftDaoJson;
    }

}
