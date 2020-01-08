package com.bw.expandebale.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.expandebale.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * DateTime:2020/1/8 0008
 * author:朱贵全(Administrator)
 * function:
 */
public class MyViewLinearLayout extends LinearLayout {
    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.jia)
    TextView jia;

    private int num=1;
    public MyViewLinearLayout(Context context) {
        super(context);
    }

    public MyViewLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.add, this);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.jian, R.id.jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jian:
                if(num>1){
                    num--;
                    number.setText(num+"");
                    if (numChange != null) {
                        numChange.change(num);
                    }
                }else{
                    Toast.makeText(getContext(), "不能再减了", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.jia:
                num++;
                number.setText(num+"");
                if (numChange != null) {
                    numChange.change(num);
                }
                break;
        }
    }

    public void setNum(int num) {
        this.num = num;
        number.setText(num+"");
    }

    NumChange numChange;

    public void setNumChange(NumChange numChange) {
        this.numChange = numChange;
    }

    public interface NumChange{
        void change(int num);
    }
}
