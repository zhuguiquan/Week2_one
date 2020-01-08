package com.bw.week2_one.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.week2_one.R;
import com.bw.week2_one.model.bean.RightBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {


    private List<RightBean.ResultBean> result1;

    public RightAdapter(List<RightBean.ResultBean> result1) {

        this.result1 = result1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.rightitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RightBean.ResultBean resultBean = result1.get(position);
        holder.tv.setText(resultBean.getCommodityName());
        Glide.with(holder.img).load(resultBean.getMasterPic()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return result1.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv)
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            img=itemView.findViewById(R.id.img);
            tv=itemView.findViewById(R.id.tv);
        }
    }




}
