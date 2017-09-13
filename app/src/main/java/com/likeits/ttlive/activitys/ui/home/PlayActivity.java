package com.likeits.ttlive.activitys.ui.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.adapter.FriendTabAdapter;
import com.likeits.ttlive.activitys.fragment.MakeFriendsFragment01;
import com.likeits.ttlive.activitys.utils.AndroidWorkaround;
import com.likeits.ttlive.activitys.utils.MyActivityManager;
import com.likeits.ttlive.activitys.utils.UtilPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.likeits.ttlive.activitys.base.Container.setMiuiStatusBarDarkMode;

public class PlayActivity extends FragmentActivity {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private PlayActivity mContext;
    private Window window;
    private String ukey;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View main = getLayoutInflater().from(this).inflate(R.layout.activity_play, null);
        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        MyActivityManager.getInstance().addActivity(this);
        setContentView(main);
        mContext = this;
        setMiuiStatusBarDarkMode(this, true);
        window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        MyActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        ukey = UtilPreference.getStringValue(mContext, "ukey");
        initView();
    }
    private void initView() {
        tvHeader.setText("陪玩");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(viewpager);
        mDatas = new ArrayList<String>(Arrays.asList("全部","线上陪玩","线下陪玩"));
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new MakeFriendsFragment01());
        mfragments.add(new MakeFriendsFragment01());
        mfragments.add(new MakeFriendsFragment01());
        viewpager.setAdapter(new FriendTabAdapter(getSupportFragmentManager(), mfragments, mDatas));
        viewpager.setCurrentItem(0);
    }
    @OnClick(R.id.backBtn)
    public void Onclick(View view){
        onBackPressed();
    }
}
