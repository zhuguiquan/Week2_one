package com.bw.expandebale.presenter;

import com.bw.expandebale.base.BasePresenter;
import com.bw.expandebale.contract.IHomeContract;
import com.bw.expandebale.model.Bean;
import com.bw.expandebale.model.HomeModel;

/**
 * DateTime:2020/1/6 0006
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
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });

    }
}
