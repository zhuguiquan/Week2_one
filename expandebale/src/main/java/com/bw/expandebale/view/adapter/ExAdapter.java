package com.bw.expandebale.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.expandebale.R;
import com.bw.expandebale.model.Bean;
import com.bw.expandebale.widget.MyViewLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class ExAdapter extends BaseExpandableListAdapter {
    private List<Bean.ResultBean> result;

    public ExAdapter(List<Bean.ResultBean> result) {

        this.result = result;
    }

    @Override
    public int getGroupCount() {
        return result.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Bean.ResultBean resultBean = result.get(i);
        List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
        return shoppingCartList.size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        boolean c = true;
        ParentHolder parentHolder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.items, null);
            parentHolder = new ParentHolder(view);
            view.setTag(parentHolder);
        } else {
            parentHolder = (ParentHolder) view.getTag();
        }

        Bean.ResultBean resultBean = result.get(i);
        parentHolder.itName.setText(resultBean.getCategoryName());
        List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
        for (int j = 0; j < shoppingCartList.size(); j++) {
            if (shoppingCartList.get(j).isChecked() == false) {
                c = false;
                break;
            }
        }
        parentHolder.check.setChecked(c);

        boolean finalC = c;
        parentHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean d= finalC;
                   d=!d;
                for (int j = 0; j <shoppingCartList.size() ; j++) {
                    shoppingCartList.get(j).setChecked(d);
                }
                notifyDataSetChanged();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick();
                }
            }
        });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder childHolder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item1, null);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        Bean.ResultBean resultBean = result.get(i);
        List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
        Bean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(i1);
        childHolder.itName.setText(shoppingCartListBean.getCommodityName());
        childHolder.itPrice.setText(shoppingCartListBean.getPrice() + "");
        Glide.with(childHolder.itImg).load(shoppingCartListBean.getPic()).into(childHolder.itImg);

        childHolder.checkChild.setChecked(shoppingCartListBean.isChecked());

        childHolder.checkChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartListBean.setChecked(!shoppingCartListBean.isChecked());
                notifyDataSetChanged();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick();
                }
            }
        });
        childHolder.addLinearLayout.setNum(shoppingCartListBean.getCount());
        childHolder.addLinearLayout.setNumChange(new MyViewLinearLayout.NumChange() {
            @Override
            public void change(int num) {
                shoppingCartListBean.setCount(num);
                notifyDataSetChanged();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick();
                }

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    static class ParentHolder {
        @BindView(R.id.it_name)
        TextView itName;
        @BindView(R.id.check)
        CheckBox check;

        ParentHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.it_img)
        ImageView itImg;
        @BindView(R.id.it_name)
        TextView itName;
        @BindView(R.id.it_price)
        TextView itPrice;
        @BindView(R.id.check_child)
        CheckBox checkChild;
        @BindView(R.id.addLiner)
        MyViewLinearLayout addLinearLayout;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //就算总价
    public float calculateTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < result.size(); i++) {
            //拿到商家
            Bean.ResultBean resultBean = result.get(i);
            //商家里的数据
            List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
            for (int j = 0; j < shoppingCartList.size(); j++) {
                if (shoppingCartList.get(j).isChecked() == true) {
                    int price = shoppingCartList.get(j).getPrice();
                    int count = shoppingCartList.get(j).getCount();
                    totalPrice += price * count;
                }
            }
        }
        return totalPrice;
    }

    //就算数量
    public int canculateTotalNumber() {
        int totalNumber = 0;
        for (int i = 0; i < result.size(); i++) {
            //拿到商家
            Bean.ResultBean resultBean = result.get(i);
            //商家里的数据
            List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
            for (int j = 0; j < shoppingCartList.size(); j++) {
                if (shoppingCartList.get(j).isChecked() == true) {
                    int count = shoppingCartList.get(j).getCount();
                    totalNumber += count;
                }
            }
        }
        return totalNumber;
    }
    //全部选中
    public boolean getIsAllChecked(){
        boolean isAllChecked=true;
        for (int i = 0; i <result.size() ; i++) {
            List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
            for (int j = 0; j <shoppingCartList.size() ; j++) {
                Bean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(j);
                if(shoppingCartListBean.isChecked()==false){
                    isAllChecked=false;
                }
                notifyDataSetChanged();
            }
        }
        return isAllChecked;
    }

    public void changeIsChecked(boolean b){
        for (int i = 0; i <result.size() ; i++) {
            List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
            for (int j = 0; j <shoppingCartList.size() ; j++) {
                Bean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(j);
               shoppingCartListBean.setChecked(b);
            }
        }

    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //接口回调
    public interface OnItemClickListener{
        void onItemClick();
    }

}
