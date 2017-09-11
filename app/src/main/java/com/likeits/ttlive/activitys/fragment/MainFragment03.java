package com.likeits.ttlive.activitys.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.likeits.ttlive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment03 extends MyBaseFragment {



    private TextView tvHeader;
    private Button btBack;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_fragment03;
    }

    @Override
    protected void lazyLoad() {
        initView();
        initListener();
    }


    private void initView() {
        btBack=findViewById(R.id.backBtn);
        btBack.setVisibility(View.GONE);
        tvHeader=findViewById(R.id.tv_header);
        tvHeader.setText("达人榜");
    }
    private void initListener() {
    }

}
