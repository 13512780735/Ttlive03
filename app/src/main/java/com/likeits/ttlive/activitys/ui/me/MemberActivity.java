package com.likeits.ttlive.activitys.ui.me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.member_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.member_gridView02)
    MyGridView mGridView02;
    private List<Map<String, Object>> dataList02;
    // 图片封装为一个数组
    private String[] cash= {"¥100","¥180" ,"¥250","¥420"};
    private String[] time= {"1 年","2 年" ,"3 年","5 年"};
    private SimpleAdapter simpleAdapter02;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("购买会员");
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
        dataList02= new ArrayList<Map<String, Object>>();
        getData02();
        String[] from02 = {"cash","time"};
        int[] to02 = {R.id.mount_gridView02_cash,R.id.mount_gridView02_time};
        simpleAdapter02 = new SimpleAdapter(mContext, dataList02, R.layout.layout_mount_gridview02_items, from02, to02);
        //配置适配器
        mGridView02.setAdapter(simpleAdapter02);
        simpleAdapter02.notifyDataSetChanged();
    }
    private List<Map<String, Object>> getData02() {
        for (int i = 0; i < cash.length; i++) {
            Log.d("TAG", "" + cash.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cash", cash[i]);
            map.put("time", time[i]);
            dataList02.add(map);
        }
        return dataList02;
    }

    @OnClick(R.id.backBtn)
    public void Onclick(View v) {
        onBackPressed();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }
}
