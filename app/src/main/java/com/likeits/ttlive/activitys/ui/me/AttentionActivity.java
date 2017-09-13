package com.likeits.ttlive.activitys.ui.me;

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
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttentionActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.attention_scrollview)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @BindView(R.id.attention_listView)
    MyListview mListView;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test01,
            R.mipmap.test04, R.mipmap.test01, R.mipmap.test04};
    private String[] iconName = {"小汤圆", "A君", "空白的", "小灰灰C", "小灰灰D"};
    private String[] iconType = {"游戏", "男女模", "游戏", "男女模", "游戏"};
    private String[] iconCharm = {"魅力值：1.5万", "魅力值：1.7万", "魅力值：1.6万", "魅力值：2.5万", "魅力值：1.2万"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("关注");
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
        String[] from = {"img", "name", "type", "charm"};
        int[] to = {R.id.contact_listview_ivAvatar, R.id.contact_listview_tvName,
                R.id.contact_listview_tv_type, R.id.contact_listview_tv_charm};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_attention_lsitview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toActivity(UserCentreActivity.class);
            }
        });
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            map.put("type", iconType[i]);
            map.put("charm", iconCharm[i]);
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
