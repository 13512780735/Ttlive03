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
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExchangeGiftActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView>{
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.exchange_gift_scrollview)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.exchange_gift_listView)
    MyListview mListView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    // 图片封装为一个数组
    private String[] type = {"VIP礼物 x1","VIP礼物 x1","VIP礼物 x1","VIP礼物 x1","VIP礼物 x1"};
    private String[] number = {"1000 红宝石", "1000 红宝石", "1000 红宝石", "1000 红宝石", "1000 红宝石"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_gift);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("兑换礼品");
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
        String[] from = {"type", "number"};
        int[] to = {R.id.tv_gift_type, R.id.tv_ruby_number};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_exchange_gift_listview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < type.length; i++) {
            Log.d("TAG", "" + type.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("type", type[i]);
            map.put("number", number[i]);
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
        ListScrollUtil.setListViewHeightBasedOnChildren(mListView);
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        ListScrollUtil.setListViewHeightBasedOnChildren(mListView);
        mPullToRefreshScrollView.onRefreshComplete();
    }
}

