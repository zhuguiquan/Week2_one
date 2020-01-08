package com.bw.expandebale.contract;

import com.bw.expandebale.model.Bean;


/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public interface IHomeContract {
    interface IView{
        void onHomeSuccess(Bean bean);
        void onHomeFailure(Throwable throwable);

    }
    interface IPresenter{
        void getHomeData();
    }
    interface IModel{
        void getHomeData(IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void onHomeSuccess(Bean bean);
            void onHomeFailure(Throwable throwable);
        }
    }
}
