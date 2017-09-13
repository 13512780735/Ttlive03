package com.likeits.ttlive.activitys.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.fragment.PayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends Container {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.pay_tv_10)
    RelativeLayout rl10;
    @BindView(R.id.pay_tv_20)
    RelativeLayout rl20;
    @BindView(R.id.pay_tv_50)
    RelativeLayout rl50;
    @BindView(R.id.pay_tv_100)
    RelativeLayout rl100;
    @BindView(R.id.pay_tv_200)
    RelativeLayout rl200;
    private PayFragment myPayFragment;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("充值");


    }

    @OnClick({R.id.backBtn,R.id.pay_tv_10,R.id.pay_tv_20,R.id.pay_tv_50,R.id.pay_tv_100,R.id.pay_tv_200})
    public void Onclick(View v) {

        switch (v.getId()){
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.pay_tv_10:
                myPayFragment = new PayFragment();
                bundle = new Bundle();
                bundle.putString("Data01", "10");
                bundle.putString("Data02", "10,000");
                myPayFragment.setArguments(bundle);
                myPayFragment.show(getSupportFragmentManager(), "PayFragment");
                break;
            case R.id.pay_tv_20:
                myPayFragment = new PayFragment();
                bundle = new Bundle();
                bundle.putString("Data01", "20");
                bundle.putString("Data02", "20,000");
                myPayFragment.setArguments(bundle);
                myPayFragment.show(getSupportFragmentManager(), "PayFragment");
                break;
            case R.id.pay_tv_50:
                myPayFragment = new PayFragment();
                bundle = new Bundle();
                bundle.putString("Data01", "50");
                bundle.putString("Data02", "50,000");
                myPayFragment.setArguments(bundle);
                myPayFragment.show(getSupportFragmentManager(), "PayFragment");
                break;
            case R.id.pay_tv_100:
                myPayFragment = new PayFragment();
                bundle = new Bundle();
                bundle.putString("Data01", "100");
                bundle.putString("Data02", "100,000");
                myPayFragment.setArguments(bundle);
                myPayFragment.show(getSupportFragmentManager(), "PayFragment");
                break;
            case R.id.pay_tv_200:
                myPayFragment = new PayFragment();
                bundle = new Bundle();
                bundle.putString("Data01", "200");
                bundle.putString("Data02", "200,000");
                myPayFragment.setArguments(bundle);
                myPayFragment.show(getSupportFragmentManager(), "PayFragment");
                break;
        }
    }
}
