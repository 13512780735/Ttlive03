package com.likeits.ttlive.activitys.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends BaseFragment implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {

    PullToRefreshScrollView mPullToRefreshScrollView;
    MyListview mListview01;
    MyListview mListview02;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private String[] name = {"小丸子mm", "小灰灰", "小东东", "小丸子dd", "小灰灰MM"};
    private String[] number = {"VIP礼物 x1", "VIP礼物 x66", "VIP礼物 x266", "VIP礼物 x10", "VIP礼物 x15"};
    private SimpleAdapter simpleAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {
        mPullToRefreshScrollView = findViewById(R.id.gfit_scrollview);
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
        mListview01 = findViewById(R.id.earnings_today_listview);
        mListview02 = findViewById(R.id.earnings_yesterday_listview);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"name", "number"};
        int[] to = {R.id.earnings_listview_tvName, R.id.earnings_listview_tvNumber};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_break_even_listview_items, from, to);
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
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }
}
