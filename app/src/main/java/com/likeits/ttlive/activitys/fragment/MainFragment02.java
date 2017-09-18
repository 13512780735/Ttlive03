package com.likeits.ttlive.activitys.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.ui.chat.CircleActivity;
import com.likeits.ttlive.activitys.ui.chat.FriendActivity;
import com.likeits.ttlive.activitys.ui.chat.NearFriendActivity;
import com.likeits.ttlive.activitys.ui.chat.NewFriendsActivity;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment02 extends MyBaseFragment implements View.OnClickListener,
        PullToRefreshBase.OnRefreshListener2<ScrollView> {


    private TextView tvHeader;
    private Button btBack;
    private RelativeLayout rlNewFriend, rlNear, rlFriend, rlCircle, rlChat, rlTrueWords;
    private PullToRefreshScrollView mPullToRefreshScrollView;
    private MyListview mListView;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.test04, R.mipmap.test04,
            R.mipmap.test04, R.mipmap.test04, R.mipmap.test04};
    private SimpleAdapter simpleAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_fragment02;
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
        tvHeader.setText("好友");
        rlNewFriend = findViewById(R.id.rl_new_friends);
        rlFriend = findViewById(R.id.rl_friends);
        rlNear = findViewById(R.id.rl_near);
        rlTrueWords = findViewById(R.id.rl_true_words);
        rlChat = findViewById(R.id.rl_chat);
        rlCircle = findViewById(R.id.rl_circle);
        mPullToRefreshScrollView = findViewById(R.id.chat_scrollView);
        mListView = findViewById(R.id.chat_listview);
    }

    private void initListener() {
        rlNewFriend.setOnClickListener(this);
        rlFriend.setOnClickListener(this);
        rlNear.setOnClickListener(this);
        rlTrueWords.setOnClickListener(this);
        rlChat.setOnClickListener(this);
        rlCircle.setOnClickListener(this);
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
        int[] to = {R.id.live_user_avatar};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_chat_listview_items, from, to);
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //新朋友
            case R.id.rl_new_friends:
                toActivity(NewFriendsActivity.class);
                break;
            //好友
            case R.id.rl_friends:
                toActivity(FriendActivity.class);
                break;
            //附近的人
            case R.id.rl_near:
                toActivity(NearFriendActivity.class);
                break;
            //真心话
            case R.id.rl_true_words:
                showIndentDialog();
                break;
            //闲聊大厅
            case R.id.rl_chat:
                break;
            //动态圈
            case R.id.rl_circle:
                toActivity(CircleActivity.class);
                break;
        }
    }

    public void showIndentDialog() {
        TrueWordsDialogFragment dialog = new TrueWordsDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "TrueWordsDialogFragment");
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
