package com.likeits.ttlive.activitys.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment01 extends MyBaseFragment implements View.OnClickListener {


    private TextView tvHead;
    private Button btBack;
    private PullToRefreshListView mPullToRefreshListView;
    ListView mListView;
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
        mPullToRefreshListView = findViewById(R.id.mian_home_listview);
        View headerView = View.inflate(getActivity(), R.layout.layout_main_home_header,
                null);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.addHeaderView(headerView);
        sliderShow = findViewById(R.id.slider);
        rlFriends = findViewById(R.id.rl_friends);
        rlGame = findViewById(R.id.rl_game);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"img", "name", "content", "type", "address", "number"};
        int[] to = {R.id.roundRectImageView, R.id.home_listview_name, R.id.home_listview_content,
                R.id.home_listview_type, R.id.home_listview_address, R.id.home_listview_number};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_home_listview_items, from, to);
        //配置适配器
        mPullToRefreshListView.setAdapter(simpleAdapter);
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
        file_maps.put("", R.drawable.bj);
        file_maps.put("", R.drawable.bj);
        file_maps.put("", R.drawable.bj);
        file_maps.put("", R.drawable.bj);
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
        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        sliderShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showS(getActivity(), "1");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_friends:
                ToastUtil.showS(getActivity(), "点击了");
                break;
            case R.id.rl_game:
                ToastUtil.showS(getActivity(), "点击了");
                break;
        }
    }
}
