package com.bw.week2_one.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.week2_one.R;
import com.bw.week2_one.model.bean.LeftBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private List<LeftBean.ResultBean.SecondCategoryVoBean> leftList;

    public LeftAdapter(List<LeftBean.ResultBean.SecondCategoryVoBean> leftList) {

        this.leftList = leftList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.leftitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LeftBean.ResultBean.SecondCategoryVoBean bean = leftList.get(position);
        holder.tv.setText(bean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClcik(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return leftList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tv=itemView.findViewById(R.id.tv);
        }
    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClcik(int position);
    }
}
