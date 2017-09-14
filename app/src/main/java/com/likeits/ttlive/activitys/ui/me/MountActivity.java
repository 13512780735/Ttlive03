package com.likeits.ttlive.activitys.ui.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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

public class MountActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.mount_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.mount_gridView)
    MyGridView mGridView01;
    @BindView(R.id.mount_gridView02)
    MyGridView mGridView02;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.icon_gift, R.mipmap.icon_gift,
            R.mipmap.icon_gift, R.mipmap.icon_gift, R.mipmap.icon_gift, R.mipmap.icon_gift, R.mipmap.icon_gift, R.mipmap.icon_gift};
    private SimpleAdapter simpleAdapter;

    private List<Map<String, Object>> dataList02;
    // 图片封装为一个数组
    private String[] cash= {"¥100","¥180" ,"¥250","¥420"};
    private String[] time= {"1 年","2 年" ,"3 年","5 年"};
    private SimpleAdapter simpleAdapter02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mount);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("购买坐骑");
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
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"img"};
        int[] to = {R.id.mount_gridView_iv_avatar};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_mount_gridview_items, from, to);
        //配置适配器
        mGridView01.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
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
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            dataList.add(map);
        }
        return dataList;
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
