package com.likeits.ttlive.activitys.ui.me;

import android.support.v7.app.AppCompatActivity;
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
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EarningsActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.earnings_scrollview)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.earnings_today_listview)
    MyListview mListview01;
    @BindView(R.id.earnings_yesterday_listview)
    MyListview mListview02;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private String[] name = {"小丸子mm","小丸子mm","小丸子mm","小丸子mm","小丸子mm"};
    private String[] number = {"+ 红宝石 x1", "+ 红宝石 x2", "+ 红宝石 x3", "+ 红宝石 x2", "+ 红宝石 x1"};
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("收益记录");
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
        String[] from = {"name", "number"};
        int[] to = {R.id.earnings_listview_tvName, R.id.earnings_listview_tvNumber};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_earnings_listview_items, from, to);
        //配置适配器
        mListview01.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        mListview02.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < name.length; i++) {
            Log.d("TAG", "" + name.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name[i]);
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
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }
}
