package com.likeits.ttlive.activitys.ui.live;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomSettingActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_room_name)
    EditText edRoomName;
    @BindView(R.id.et_room_introduce)
    EditText edRoomIntroduce;
    @BindView(R.id.ed_room_notice)
    EditText edRoomNotice;
    @BindView(R.id.tv_room_length)
    TextView tvRoomLength;
    @BindView(R.id.tv_room01_length)
    TextView tvRoomIntroduceLength;
    @BindView(R.id.tv_room02_length)
    TextView tvRoomNoticeLength;
    @BindView(R.id.room_setting_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("编辑直播间");
        tvRight.setText("完成");
        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshScrollView.setOnRefreshListener(this);
        mPullToRefreshScrollView.getLoadingLayoutProxy().setLastUpdatedLabel(
                "上次刷新时间");
        mPullToRefreshScrollView.getLoadingLayoutProxy()
                .setPullLabel("下拉刷新");
//          mPullRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel(
//                      "refreshingLabel");
        mPullToRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel(
                "松开即可刷新");
        edRoomName.addTextChangedListener(mTextWatcher);
        edRoomIntroduce.addTextChangedListener(mTextWatcher1);
        edRoomNotice.addTextChangedListener(mTextWatcher2);
    }

    @OnClick({R.id.backBtn, R.id.tv_right})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.tv_right:
                onBackPressed();
                break;
        }

    }
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//          mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = edRoomName.getSelectionStart();
            editEnd = edRoomName.getSelectionEnd();
            tvRoomLength.setText("" + temp.length());
            if (temp.length() > 15) {
                Toast.makeText(mContext,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                edRoomName.setText(s);
                edRoomName.setSelection(tempSelection);
            }
        }
    };
    TextWatcher mTextWatcher1 = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//          mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = edRoomIntroduce.getSelectionStart();
            editEnd = edRoomIntroduce.getSelectionEnd();
            tvRoomIntroduceLength.setText("" + temp.length());
            if (temp.length() > 15) {
                Toast.makeText(mContext,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                edRoomIntroduce.setText(s);
                edRoomIntroduce.setSelection(tempSelection);
            }
        }
    };
    TextWatcher mTextWatcher2 = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//          mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = edRoomNotice.getSelectionStart();
            editEnd = edRoomNotice.getSelectionEnd();
            tvRoomNoticeLength.setText("" + temp.length());
            if (temp.length() > 50) {
                Toast.makeText(mContext,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                edRoomNotice.setText(s);
                edRoomNotice.setSelection(tempSelection);
            }
        }
    };
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }
}
