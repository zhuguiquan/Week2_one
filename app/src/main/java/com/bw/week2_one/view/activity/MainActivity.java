package com.bw.week2_one.view.activity;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.week2_one.R;
import com.bw.week2_one.base.BaseActivity;
import com.bw.week2_one.contract.IHomeContract;
import com.bw.week2_one.database.DaoMaster;
import com.bw.week2_one.database.DaoSession;
import com.bw.week2_one.database.LeftDaoDao;
import com.bw.week2_one.database.RightDaoDao;
import com.bw.week2_one.model.bean.LeftBean;
import com.bw.week2_one.model.bean.RightBean;
import com.bw.week2_one.model.dao.LeftDao;
import com.bw.week2_one.model.dao.RightDao;
import com.bw.week2_one.presenter.HomePresenter;
import com.bw.week2_one.util.NetUtil;
import com.bw.week2_one.view.adapter.LeftAdapter;
import com.bw.week2_one.view.adapter.RightAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;
    private LeftDaoDao leftDaoDao;
    private RightDaoDao rightDaoDao;


    @Override
    protected void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        leftDaoDao = daoSession.getLeftDaoDao();
        rightDaoDao = daoSession.getRightDaoDao();
        if(NetUtil.getInstance().hasNet(this)){
            mPresenter.getHomeData();
        }else{
            //左边
            LeftDao unique = leftDaoDao.queryBuilder().unique();
            String leftDaoJson = unique.getLeftDaoJson();
            LeftBean leftBean = new Gson().fromJson(leftDaoJson, LeftBean.class);
            List<LeftBean.ResultBean> result = leftBean.getResult();
            List<LeftBean.ResultBean.SecondCategoryVoBean> leftList = result.get(0).getSecondCategoryVo();
            rv1.setLayoutManager(new LinearLayoutManager(this));
            LeftAdapter leftAdapter = new LeftAdapter(leftList);
            leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
                @Override
                public void onItemClcik(int position) {
                //    EventBus.getDefault().post(leftList.get(position).getId());
                    List<RightDao> list = rightDaoDao.queryBuilder().list();
                    RightDao rightDao = list.get(position);
                    String rightDaoJson = rightDao.getRightDaoJson();
                    RightBean rightBean = new Gson().fromJson(rightDaoJson, RightBean.class);
                    List<RightBean.ResultBean> result1 = rightBean.getResult();
                    rv2.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    RightAdapter rightAdapter = new RightAdapter(result1);
                    rv2.setAdapter(rightAdapter);
                }
            });
            rv1.setAdapter(leftAdapter);
        }
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
        return R.layout.activity_main;
    }

    @Override
    public void onHomeSuccess(LeftBean bean) {
        leftDaoDao.deleteAll();
        List<LeftBean.ResultBean> result = bean.getResult();
        List<LeftBean.ResultBean.SecondCategoryVoBean> leftList = result.get(0).getSecondCategoryVo();
        rv1.setLayoutManager(new LinearLayoutManager(this));
        LeftAdapter leftAdapter = new LeftAdapter(leftList);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClcik(int position) {
                EventBus.getDefault().post(leftList.get(position).getId());
            }
        });
        rv1.setAdapter(leftAdapter);
        String id = leftList.get(0).getId();
        mPresenter.getRightData(id);
          //存储
        String lefts = new Gson().toJson(bean);
        LeftDao leftDao = new LeftDao(lefts);
        leftDaoDao.insert(leftDao);



    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Log.i("xxx","失败");

    }

    @Override
    public void onRightSuccess(RightBean rightBean) {
        List<RightBean.ResultBean> result1 = rightBean.getResult();
        rv2.setLayoutManager(new GridLayoutManager(this,2));
        RightAdapter rightAdapter = new RightAdapter(result1);
        rv2.setAdapter(rightAdapter);
       //存储
        String right = new Gson().toJson(rightBean);
        RightDao rightDao = new RightDao(right);
        rightDaoDao.insert(rightDao);

    }

    @Override
    public void onRightFailure(Throwable throwable) {
        Log.i("xxx","失败");

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
     public void event(String ss){
        mPresenter.getRightData(ss);

    }

}
