package com.bw.week2_one.contract;

import com.bw.week2_one.model.bean.LeftBean;
import com.bw.week2_one.model.bean.RightBean;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public interface IHomeContract {
    interface IView{
        void onHomeSuccess(LeftBean bean);
        void onHomeFailure(Throwable throwable);
        void onRightSuccess(RightBean rightBean);
        void onRightFailure(Throwable throwable);

    }
    interface IPresenter{
        void getHomeData();
        void getRightData(String id);
    }
    interface IModel{
        void getHomeData(IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void onHomeSuccess(LeftBean bean);
            void onHomeFailure(Throwable throwable);
        }
        void getRightData(String id,IRightModelCallBack iRightModelCallBack);
        interface IRightModelCallBack{
            void onHomeSuccess(RightBean rightBean);
            void onHomeFailure(Throwable throwable);
        }
    }
}
