package com.likeits.ttlive.activitys.ui.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyErederActivity extends Container {
    @BindView(R.id.tv_header)
    TextView tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_ereder);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("申请达人");
    }

    @OnClick(R.id.backBtn)
    public void Onclick(View v) {
        onBackPressed();
    }
}
