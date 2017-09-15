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
import com.likeits.ttlive.activitys.custom.CommandPhotoUtil;
import com.likeits.ttlive.activitys.custom.CustomScrollGridView;
import com.likeits.ttlive.activitys.custom.GridAdapter;
import com.likeits.ttlive.activitys.custom.PhotoSystemOrShoot;
import com.likeits.ttlive.activitys.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCentre01Activity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.user_centre_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    //图片添加
    //照片墙
    @BindView(R.id.photo_gridview)
    MyGridView mGridView;
    //礼品
    @BindView(R.id.user_centre_gift_gridview)
    MyGridView mGridView03;
    //个人动态
    @BindView(R.id.trends_gridview)
    MyGridView mGridView02;
    /**
     * GridView适配器
     */
    private GridAdapter gridAdapter;

    /**
     * 管理图片操作
     */
    private CommandPhotoUtil commandPhoto;

    /**
     * 选择图片来源
     */
    private PhotoSystemOrShoot selectPhoto;
    private String cid;
    private int selectPosition = -1;//用于记录用户选择的变量

    /**
     * VIP收到的禮物模擬數據
     * @param savedInstanceState
     */
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.icon_me_gift, R.mipmap.icon_me_gift,
            R.mipmap.icon_me_gift, R.mipmap.icon_me_gift, R.mipmap.icon_me_gift};
    private String[] iconName = {"VIP坐骑", "VIP坐骑", "VIP坐骑", "VIP坐骑", "VIP坐骑"};
    private List<Map<String, Object>> dataList03;
    private SimpleAdapter simpleAdapter03;
    // 个人动态
    private int[] icon01 = {R.drawable.test05, R.drawable.test05,
            R.drawable.test05,R.drawable.test05,R.drawable.test05};
    private List<Map<String, Object>> dataList02;
    private SimpleAdapter simpleAdapter02;
    //照片墻
    private int[] icon03 = {R.drawable.test05, R.drawable.test05,
            R.drawable.test05,R.drawable.test05};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_centre01);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        tvHeader.setText("小灰灰");
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
        /**
         * 礼品
         */
        dataList03 = new ArrayList<Map<String, Object>>();
        getData03();
        String[] from = {"img", "name"};
        int[] to = {R.id.user_centre_gift_gridview_ivAvatar, R.id.user_centre_gift_gridview_tvName};
        simpleAdapter03 = new SimpleAdapter(mContext, dataList03, R.layout.layout_user_centre_gift_gridview, from, to);
        //配置适配器
        mGridView03.setAdapter(simpleAdapter03);
        simpleAdapter03.notifyDataSetChanged();
        /**
         * 个人动态
         */
        dataList02 = new ArrayList<Map<String, Object>>();
        getData02();
        String[] from02 = {"img"};
        int[] to02 = {R.id.trends_gridview_avatar};
        simpleAdapter02 = new SimpleAdapter(mContext, dataList02, R.layout.layout_trends_gridview_items, from02, to02);
        //配置适配器
        mGridView02.setAdapter(simpleAdapter02);
        simpleAdapter02.notifyDataSetChanged();
        /**
         * 照片墙
         */
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from03 = {"img"};
        int[] to03 = {R.id.iv_photo};
        simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_user_centre_photo_gridview_items, from03, to03);
        //配置适配器
        mGridView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon03.length; i++) {
            Log.d("TAG", "" + icon03.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon03[i]);
            dataList.add(map);
        }
        return dataList;
    }

    private List<Map<String, Object>> getData02() {
        for (int i = 0; i < icon01.length; i++) {
            Log.d("TAG", "" + icon01.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon01[i]);
            dataList02.add(map);
        }
        return dataList02;
    }
    private List<Map<String, Object>> getData03() {
        for (int i = 0; i < icon.length; i++) {
            Log.d("TAG", "" + icon.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            dataList03.add(map);
        }
        return dataList03;
    }


    @OnClick({R.id.backBtn})
    public void Onclick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                onBackPressed();
                break;
        }

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
