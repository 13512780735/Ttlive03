package com.likeits.ttlive.activitys.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.utils.ListScrollUtil;
import com.likeits.ttlive.activitys.utils.ToastUtil;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment01 extends MyBaseFragment implements View.OnClickListener,
        PullToRefreshBase.OnRefreshListener2<ScrollView> {


    private TextView tvHead;
    private Button btBack;
    private PullToRefreshScrollView mPullToRefreshScrollView;
    MyListview mListView;
    private SliderLayout sliderShow;
    private RelativeLayout rlFriends, rlGame;
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
        return R.layout.fragment_main_fragment01;
    }

    @Override
    protected void lazyLoad() {
        initView();
        imageSlider();
        initListener();
    }


    private void initView() {
        btBack = findViewById(R.id.backBtn);
        tvHead = findViewById(R.id.tv_header);
        btBack.setVisibility(View.GONE);
        tvHead.setText("首页");
        mPullToRefreshScrollView = findViewById(R.id.ll_home_scrollview);
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
        mListView = findViewById(R.id.main_home_listview);
        sliderShow = findViewById(R.id.slider);
        rlFriends = findViewById(R.id.rl_friends);
        rlGame = findViewById(R.id.rl_game);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"img", "name", "content", "type", "address", "number"};
        int[] to = {R.id.roundRectImageView, R.id.home_listview_name, R.id.home_listview_content,
                R.id.home_listview_type, R.id.home_listview_address, R.id.home_listview_number};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_home_listview_items, from, to);
        //配置适配器
        mListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();

    }

    private void initListener() {
        rlFriends.setOnClickListener(this);
        rlGame.setOnClickListener(this);
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

    private void imageSlider() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("", R.mipmap.home_ad_bg);
        file_maps.put("", R.mipmap.home_ad_bg);
//        for (int i = 0; i < file_maps.size(); i++) {
//            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
//            // textSliderView.description("");//设置标题
//            defaultSliderView.image(file_maps.get(i));//设置图片的网络地址
//            //添加到布局中显示
//            sliderShow.addSlider(defaultSliderView);
//        }
        for (String name : file_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView
                    .image(file_maps.get(name));
            sliderShow.addSlider(defaultSliderView);
        }
        //其他设置
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//使用默认指示器，在底部显示
        sliderShow.setDuration(5000);//停留时间
        sliderShow.setPresetTransformer(SliderLayout.Transformer.Default);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sliderShow.stopAutoCycle();
        sliderShow.destroyDrawingCache();
    }

    public void showIndentDialog1() {
        MakeFriendsFragment dialog = new MakeFriendsFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "MakeFriendsFragment");
    }

    public void showIndentDialog2() {
        PlayFragment dialog = new PlayFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "PlayFragment");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_friends:
                showIndentDialog1();
                //  ToastUtil.showS(getActivity(), "点击了");
                break;
            case R.id.rl_game:
                showIndentDialog2();
                //ToastUtil.showS(getActivity(), "点击了");
                break;
        }
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
