package com.likeits.ttlive.activitys.ui.chat;

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

public class NearFriendActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.new_friends_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.new_friends_listview)
    MyListview mListView;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test04,
            R.mipmap.test04, R.mipmap.test04, R.mipmap.test04};
    private String[] name = {"小灰灰", "小灰灰", "小灰灰", "小灰灰", "小灰灰"};
    private int[] icon01 = {R.mipmap.icon_me_boy, R.mipmap.icon_me_girl,
            R.mipmap.icon_me_boy, R.mipmap.icon_me_girl, R.mipmap.icon_me_boy};
    private String[] distance = {"568米以内", "580米以内", "583米以内", "590米以内", "595米以内"};
    private SimpleAdapter simpleAdapter;
    private String name1;
    private String flag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_friend);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("附近的人");
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
        String[] from = {"img", "name", "icon","distance"};
        int[] to = {R.id.me_header_avatar, R.id.me_header_name, R.id.me_header_sex,R.id.me_header_distance};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_near_friend_listview_items, from, to);
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();

    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", name[i]);
            map.put("icon", icon01[i]);
            map.put("distance", distance[i]);
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
