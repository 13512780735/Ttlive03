package com.likeits.ttlive.activitys.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.adapter.HomeViewPagerAdapter;
import com.likeits.ttlive.activitys.fragment.MainFragment01;
import com.likeits.ttlive.activitys.fragment.MainFragment02;
import com.likeits.ttlive.activitys.utils.MyActivityManager;
import com.likeits.ttlive.activitys.view.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.home_viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.rbHome)
    RadioButton mRbHome;
    @BindView(R.id.rbChat)
    RadioButton mRbChat;
    @BindView(R.id.rbContact)
    RadioButton mRbContact;
    @BindView(R.id.rbMe)
    RadioButton mRbMe;
    @BindView(R.id.rgTools)
    RadioGroup mRgTools;

    private HomeViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        initView();
        mViewPager.setOffscreenPageLimit(2);
        //注册一个监听连接状态的listener
        //   EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }

    //实现ConnectionListener接口
//    private class MyConnectionListener implements EMConnectionListener {
//        @Override
//        public void onConnected() {
//        }
//
//        @Override
//        public void onDisconnected(final int error) {
//            runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    if (error == EMError.USER_REMOVED) {
//                        // 显示帐号已经被移除
//                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
//                        // 显示帐号在其他设备登录
//                        //Log.d("TAG","您的账号已在其他设备登录！");
//                        //  ToastUtil.showS(mContext,"您的账号已在其他设备登录！");
//                        Intent intent = new Intent(MainActivity.this, GuideActivity.class);
//                        intent.putExtra("online", "1");
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        if (NetUtils.hasNetwork(MainActivity.this)) ;
//                            //连接不到聊天服务器
//                        else {
//                            //当前网络不可用，请检查网络设置
//                            //
//                        }
//                    }
//                }
//            });
//        }
//    }
    private void initView() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(0);
        mRgTools.setOnCheckedChangeListener(this);
        MainFragment01 fragment = new MainFragment01();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mRgTools.check(mRgTools.getChildAt(position).getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        mViewPager.setCurrentItem(radioGroup.indexOfChild(radioGroup.findViewById(checkedId)), false);
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    //MyActivityManager.getInstance().moveTaskToBack(mContext);// 不退出，后台运行
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
