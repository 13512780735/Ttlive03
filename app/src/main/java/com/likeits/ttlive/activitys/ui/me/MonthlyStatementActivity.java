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
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthlyStatementActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.monthlyStatement_scrollview)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.monthlyStatement_listView)
    MyListview mListView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private String[] time = {"09 / 2017", "08 / 2017", "07 / 2017"};
    private String[] total = {"+95.59", "+85.59", "+78.58"};
    private String[] income = {"+98.00", "+86.00", "+88.00"};
    private String[] pay = {"+26.00", "+30.00", "+36.00"};
    private String[] expense = {"-46.00", "-43.00", "-48.00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_statement);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("月结记录");
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
        String[] from = {"time", "total", "income", "pay", "expense"};
        int[] to = {R.id.monthlyStatement_listView_time, R.id.monthlyStatement_listView_total_income, R.id.monthlyStatement_listView_gift_income,
                R.id.monthlyStatement_listView_pay, R.id.monthlyStatement_listView_expense};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_monthly_statement_listview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < time.length; i++) {
            Log.d("TAG", "" + time.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", time[i]);
            map.put("total", total[i]);
            map.put("income", income[i]);
            map.put("pay", pay[i]);
            map.put("expense", expense[i]);
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
