package com.likeits.ttlive.activitys.ui.live;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.event.MessageEvent;
import com.likeits.ttlive.activitys.view.MyListview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveUserListActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.live_user_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.live_user_listview)
    MyListview mListView;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test04,
            R.mipmap.test04, R.mipmap.test04, R.mipmap.test04};
    private String[] name= {"小灰灰","小灰灰" ,"小灰灰","小灰灰","小灰灰"};
    private SimpleAdapter simpleAdapter;
    private String name1;
    private String flag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_user_list);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        name1=intent.getStringExtra("name");
        flag1=intent.getStringExtra("flag");
        initView();
    }

    private void initView() {
        tvHeader.setText("选择抱上台的用户");
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
        String[] from = {"img", "name"};
        int[] to = {R.id.live_user_avatar, R.id.live_user_name};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_live_user_listview_items, from, to);
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item;
                if("1".equals(name1)&&"flag1".equals(flag1)){
                    item="1";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }else if("2".equals(name1)&&"flag2".equals(flag1)){
                    item="2";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }else if("3".equals(name1)&&"flag3".equals(flag1)){
                    item="3";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }else if("4".equals(name1)&&"flag4".equals(flag1)){
                    item="4";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }else if("5".equals(name1)&&"flag5".equals(flag1)){
                    item="5";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }else if("6".equals(name1)&&"flag6".equals(flag1)){
                    item="6";
                    EventBus.getDefault().post(new MessageEvent(item));
                    finish();
                }

            }
        });
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", name[i]);
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
