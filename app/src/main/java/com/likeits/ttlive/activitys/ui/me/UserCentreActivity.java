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

public class UserCentreActivity extends Container {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.tv_right)
    TextView tvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_centre);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvRight.setText("编辑");
        tvHeader.setText("小灰灰");
    }

    @OnClick(R.id.backBtn)
    public void Onclick(View v) {
        onBackPressed();
    }

}
