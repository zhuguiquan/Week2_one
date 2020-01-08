package com.bw.week2_one.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), layouutId(), null);
        mPresenter=proviePresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initView(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(View inflate);

    protected abstract P proviePresenter();

    protected abstract int layouutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
