package com.bw.expandebale.base;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter() {
       initModel();
    }

    protected abstract void initModel();

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }
}
