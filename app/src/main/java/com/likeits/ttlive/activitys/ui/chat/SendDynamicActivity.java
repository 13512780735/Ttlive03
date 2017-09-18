package com.likeits.ttlive.activitys.ui.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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
import com.likeits.ttlive.activitys.custom.CommandPhotoUtil;
import com.likeits.ttlive.activitys.custom.CustomScrollGridView;
import com.likeits.ttlive.activitys.custom.GridAdapter;
import com.likeits.ttlive.activitys.custom.PhotoSystemOrShoot;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendDynamicActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_circle_message)
    EditText edMessage;
    @BindView(R.id.tv_circle_length)
    TextView tvLength;


    @BindView(R.id.circle_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    //图片添加
    @BindView(R.id.gv_all_photo)
    CustomScrollGridView mGridView;

    /**
     * GridView适配器
     */
    private GridAdapter gridAdapter;

    /**
     * 管理图片操作
     */
    private CommandPhotoUtil commandPhoto;

    /**
     * 选择图片来源
     */
    private PhotoSystemOrShoot selectPhoto;
    private String cid;
    private int selectPosition = -1;//用于记录用户选择的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_dynamic);
        ButterKnife.bind(this);
        initView();
        addPlus();
    }

    private void initView() {
        tvHeader.setText("发动态");
        tvRight.setText("发布");
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
        edMessage.addTextChangedListener(mTextWatcher);
    }

    @OnClick({R.id.backBtn, R.id.tv_right})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.tv_right:
                break;
        }

    }

    /**
     * 实例化组件
     */
    private void addPlus() {
        gridAdapter = new GridAdapter(mContext, 4);
        mGridView.setAdapter(gridAdapter);

        // 选择图片获取途径
        selectPhoto = new PhotoSystemOrShoot(mContext) {
            @Override
            public void onStartActivityForResult(Intent intent, int requestCode) {
                startActivityForResult(intent, requestCode);
            }
        };
        commandPhoto = new CommandPhotoUtil(mContext, mGridView, gridAdapter, selectPhoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 获取照片返回
        if (selectPhoto != null) {
            String photoPath = selectPhoto.getPhotoResultPath(requestCode, resultCode, data);
            if (!TextUtils.isEmpty(photoPath)) {
                commandPhoto.showGridPhoto(photoPath);
            }
        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

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
            editStart = edMessage.getSelectionStart();
            editEnd = edMessage.getSelectionEnd();
            tvLength.setText("" + temp.length());
            if (temp.length() > 250) {
                Toast.makeText(mContext,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                edMessage.setText(s);
                edMessage.setSelection(tempSelection);
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
