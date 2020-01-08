package com.bw.week2_one.model;

import com.bw.week2_one.contract.IHomeContract;
import com.bw.week2_one.model.bean.LeftBean;
import com.bw.week2_one.model.bean.RightBean;
import com.bw.week2_one.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(IModelCallBack iModelCallBack) {
        NetUtil.getInstance().getApi().getLeft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeftBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        iModelCallBack.onHomeSuccess(leftBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallBack.onHomeFailure(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getRightData(String id,IRightModelCallBack iRightModelCallBack) {
        NetUtil.getInstance().getApi().getRight(id,1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        iRightModelCallBack.onHomeSuccess(rightBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iRightModelCallBack.onHomeFailure(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
