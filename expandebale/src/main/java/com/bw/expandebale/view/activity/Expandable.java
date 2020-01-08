package com.bw.expandebale.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.expandebale.R;
import com.bw.expandebale.base.BaseActivity;
import com.bw.expandebale.contract.IHomeContract;
import com.bw.expandebale.model.Bean;
import com.bw.expandebale.presenter.HomePresenter;
import com.bw.expandebale.view.adapter.ExAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Expandable extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.ev)
    ExpandableListView ev;
    @BindView(R.id.ch)
    CheckBox ch;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.bt)
    Button bt;
    private ExAdapter exAdapter;

    @Override
    protected void initData() {
        mPresenter.getHomeData();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter provideMpresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_expandable;
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        List<Bean.ResultBean> result = bean.getResult();
        exAdapter = new ExAdapter(result);
        exAdapter.setOnItemClickListener(new ExAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                price.setText("￥："+ exAdapter.calculateTotalPrice());
                bt.setText("去结算:("+ exAdapter.canculateTotalNumber()+")");
                ch.setChecked(exAdapter.getIsAllChecked());
            }
        });
        ev.setAdapter(exAdapter);
        for (int i = 0; i < result.size(); i++) {
            ev.expandGroup(i);
        }
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exAdapter!=null){
                    boolean b=exAdapter.getIsAllChecked();
                    b=!b;
                    exAdapter.changeIsChecked(b);
                    price.setText("￥："+ exAdapter.calculateTotalPrice());
                    bt.setText("去结算:("+ exAdapter.canculateTotalNumber()+")");
                }
            }
        });

    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Log.i("xxx", "失败");
    }


}
