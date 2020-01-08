package com.bw.week2_one.presenter;

import com.bw.week2_one.base.BasePresenter;
import com.bw.week2_one.contract.IHomeContract;
import com.bw.week2_one.model.HomeModel;
import com.bw.week2_one.model.bean.LeftBean;
import com.bw.week2_one.model.bean.RightBean;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.IModelCallBack() {
            @Override
            public void onHomeSuccess(LeftBean bean) {
                view.onHomeSuccess(bean);

            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);

            }
        });

    }

    @Override
    public void getRightData(String id) {
        homeModel.getRightData(id,new IHomeContract.IModel.IRightModelCallBack() {
            @Override
            public void onHomeSuccess(RightBean rightBean) {
                view.onRightSuccess(rightBean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onRightFailure(throwable);

            }
        });
    }
}
