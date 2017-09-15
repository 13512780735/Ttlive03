package com.likeits.ttlive.activitys.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.ui.live.UserLiveActivity;
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeFriendsFragment01 extends BaseFragment implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    PullToRefreshScrollView mPullToRefreshScrollView;
    private MyListview mListView;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test04,
            R.mipmap.test04, R.mipmap.test04, R.mipmap.test04};
    private String[] iconName = {"小汤圆的直播间", "A君的直播间", "空白的直播间", "A君的直播间", "空白的直播间"};
    private String[] iconContent = {"女神直播，天籁美声，人见人爱", "女神直播，天籁美声，人见人爱", "女神直播，天籁美声，人见人爱", "女神直播，天籁美声，人见人爱", "女神直播，天籁美声，人见人爱"};
    private String[] iconType = {"房间类型：游戏", "房间类型：游戏",
            "房间类型：游戏", "房间类型：游戏", "房间类型：游戏"};
    private String[] iconAddress = {"中山市", "中山市", "中山市", "中山市", "中山市"};
    private String[] iconNumber = {"20万", "23万", "15万", "18万", "25万"};
    private SimpleAdapter simpleAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_make_friends_fragment01;
    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {
        mPullToRefreshScrollView = findViewById(R.id.friends_scrollview);
        mListView = findViewById(R.id.friend_listview);
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
        String[] from = {"img", "name", "content", "type", "address", "number"};
        int[] to = {R.id.roundRectImageView, R.id.home_listview_name, R.id.home_listview_content,
                R.id.home_listview_type, R.id.home_listview_address, R.id.home_listview_number};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_home_listview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toActivity(UserLiveActivity.class);
            }
        });
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            map.put("content", iconContent[i]);
            map.put("type", iconType[i]);
            map.put("address", iconAddress[i]);
            map.put("number", iconNumber[i]);
            dataList.add(map);
        }
        return dataList;
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
