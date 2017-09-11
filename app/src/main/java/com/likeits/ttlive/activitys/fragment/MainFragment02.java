package com.likeits.ttlive.activitys.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.likeits.ttlive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment02 extends MyBaseFragment implements View.OnClickListener {


    private TextView tvHeader;
    private Button btBack;
    private RelativeLayout rlNewFriend,rlNear,rlFriend,rlCircle,rlChat,rlTrueWords;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_fragment02;
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
        tvHeader.setText("好友");
        rlNewFriend=findViewById(R.id.rl_new_friends);
        rlFriend=findViewById(R.id.rl_friends);
        rlNear=findViewById(R.id.rl_near);
        rlTrueWords=findViewById(R.id.rl_true_words);
        rlChat=findViewById(R.id.rl_chat);
        rlCircle=findViewById(R.id.rl_circle);
    }
    private void initListener() {
        rlNewFriend.setOnClickListener(this);
        rlFriend.setOnClickListener(this);
        rlNear.setOnClickListener(this);
        rlTrueWords.setOnClickListener(this);
        rlChat.setOnClickListener(this);
        rlCircle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //新朋友
            case R.id.rl_new_friends:
                break;
            //好友
            case R.id.rl_friends:
                break;
            //附近的人
            case R.id.rl_near:
                break;
            //真心话
            case R.id.rl_true_words:
                break;
            //闲聊大厅
            case R.id.rl_chat:
                break;
            //动态圈
            case R.id.rl_circle:
                break;
        }
    }
}
