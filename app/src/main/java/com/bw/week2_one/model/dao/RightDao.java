package com.bw.week2_one.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
@Entity
public class RightDao {
    private String rightDaoJson;

    @Generated(hash = 799645474)
    public RightDao(String rightDaoJson) {
        this.rightDaoJson = rightDaoJson;
    }

    @Generated(hash = 633269940)
    public RightDao() {
    }

    public String getRightDaoJson() {
        return this.rightDaoJson;
    }

    public void setRightDaoJson(String rightDaoJson) {
        this.rightDaoJson = rightDaoJson;
    }

}
