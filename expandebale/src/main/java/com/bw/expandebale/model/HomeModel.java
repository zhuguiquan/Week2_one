package com.bw.expandebale.model;

import com.bw.expandebale.contract.IHomeContract;
import com.bw.expandebale.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(IModelCallBack iModelCallBack) {
        NetUtil.getInstance().getApi().getLeft("13112","157846360854213112")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        iModelCallBack.onHomeSuccess(bean);

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
}
