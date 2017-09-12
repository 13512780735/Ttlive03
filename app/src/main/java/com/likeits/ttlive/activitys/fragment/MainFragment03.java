package com.likeits.ttlive.activitys.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment03 extends MyBaseFragment implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {


    private TextView tvHeader;
    private Button btBack;
    private MyListview mListView;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test01,
            R.mipmap.test04, R.mipmap.test01, R.mipmap.test04};
    private String[] iconRank = {"4", "5", "6", "7", "8"};
    private String[] iconName = {"小汤圆", "A君", "空白的", "小灰灰C", "小灰灰D"};
    private String[] iconType = {"游戏", "男女模", "游戏", "男女模", "游戏"};
    private String[] iconCharm = {"魅力值：1.5万", "魅力值：1.7万", "魅力值：1.6万", "魅力值：2.5万", "魅力值：1.2万"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private PullToRefreshScrollView mPullToRefreshScrollView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_fragment03;
    }

    @Override
    protected void lazyLoad() {
        initView();
        initListener();
    }


    private void initView() {
        btBack = findViewById(R.id.backBtn);
        btBack.setVisibility(View.GONE);
        tvHeader = findViewById(R.id.tv_header);
        tvHeader.setText("达人榜");
        mPullToRefreshScrollView = findViewById(R.id.ll_contact_scrollview);
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
        mListView = findViewById(R.id.home_contacts_listview);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"rank", "img", "name", "type", "charm"};
        int[] to = {R.id.contact_listview_tvRanking, R.id.contact_listview_ivAvatar, R.id.contact_listview_tvName,
                R.id.contact_listview_tv_type, R.id.contact_listview_tv_charm};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_contact_listview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rank", iconRank[i]);
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            map.put("type", iconType[i]);
            map.put("charm", iconCharm[i]);
            dataList.add(map);
        }
        return dataList;
    }

    private void initListener() {
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
