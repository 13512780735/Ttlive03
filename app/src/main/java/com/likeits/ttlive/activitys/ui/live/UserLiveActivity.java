package com.likeits.ttlive.activitys.ui.live;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.ui.me.UserCentre01Activity;
import com.likeits.ttlive.activitys.utils.ToastUtil;
import com.likeits.ttlive.activitys.view.CircleImageView;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserLiveActivity extends Container  {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.iv_header_right)
    ImageView ivRight;
    //关注
    @BindView(R.id.rl_room_attention)
    RelativeLayout rlAttention;
    //加好友
    @BindView(R.id.rl_room_friend)
    RelativeLayout rlFriend;
    //头像
    @BindView(R.id.rl_room_details_Avatar)
    RelativeLayout rlAvatar;
    //添加用户按钮
    @BindView(R.id.room_live_iv_add01)
    CircleImageView iv01;
    @BindView(R.id.room_live_iv_add02)
    CircleImageView iv02;
    @BindView(R.id.room_live_iv_add03)
    CircleImageView iv03;
    @BindView(R.id.room_live_iv_add04)
    CircleImageView iv04;
    @BindView(R.id.room_live_iv_add05)
    CircleImageView iv05;
    @BindView(R.id.room_live_iv_add06)
    CircleImageView iv06;


    @BindView(R.id.room_live_listview)
    MyListview mListView;
    private View layoutMenu;
    private MyListview popMenuList;
    private PopupWindow popMenu;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private String[] Name = {"房间信息", "举报房间", "退出房间"};

    /**
     * 聊天信息模拟数据
     * @param savedInstanceState
     */
    private List<Map<String, Object>> dataList01;
    private SimpleAdapter simpleAdapter01;
    private String[] name01 = {"Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:", "Lv15 小灰灰mm:"};
    private String[] content01 = {"你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了", "你傻了"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_live);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("小丸子mm的直播间");
        ivRight.setImageResource(R.drawable.icon_live_header_right);
        dataList01 = new ArrayList<Map<String, Object>>();
        getData01();
        String[] from01 = {"name", "content"};
        int[] to01 = {R.id.tv_name, R.id.tv_content};
        simpleAdapter01 = new SimpleAdapter(mContext, dataList01, R.layout.layout_live_room_listview_items, from01, to01);
        //配置适配器
        mListView.setAdapter(simpleAdapter01);
        simpleAdapter01.notifyDataSetChanged();
    }
    private List<Map<String, Object>> getData01() {
        for (int i = 0; i < name01.length; i++) {
            Log.d("TAG", "" + name01.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name01[i]);
            map.put("content", content01[i]);
            dataList01.add(map);
        }
        return dataList01;
    }
    @OnClick({R.id.backBtn, R.id.iv_header_right,R.id.rl_room_details_Avatar,R.id.rl_room_attention,R.id.room_live_iv_add01,
            R.id.room_live_iv_add02,R.id.room_live_iv_add03,R.id.room_live_iv_add04,R.id.room_live_iv_add05,R.id.room_live_iv_add06,})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.iv_header_right:
                selectMenu();
                break;
            case R.id.rl_room_attention:
              ToastUtil.showS(mContext,"关注成功");
                break;
            case R.id.rl_room_details_Avatar:
               toActivity(UserCentre01Activity.class);
                break;
            case R.id.room_live_iv_add01:
                break;
            case R.id.room_live_iv_add02:
                break;
            case R.id.room_live_iv_add03:
                break;
            case R.id.room_live_iv_add04:
                break;
            case R.id.room_live_iv_add05:
                break;
            case R.id.room_live_iv_add06:
                break;
        }
    }

    private void selectMenu() {
        if (popMenu != null && popMenu.isShowing()) {
            popMenu.dismiss();
        } else {

            layoutMenu = this.getLayoutInflater().inflate(
                    R.layout.operationinto_popmenulist, null);
            popMenuList = (MyListview) layoutMenu
                    .findViewById(R.id.menulist);
            dataList = new ArrayList<Map<String, Object>>();
            getData();
            String[] from = {"name"};
            int[] to = {R.id.tv_name};
            simpleAdapter = new SimpleAdapter(mContext, dataList, R.layout.layout_live_pop_listview_items, from, to);
            //配置适配器
            popMenuList.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            // 点击listview中item的处理
            popMenuList
                    .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent,
                                                View view, int position, long id) {
                            // 隐藏弹出窗口
                            if (popMenu != null && popMenu.isShowing()) {
                                popMenu.dismiss();
                            }
                            Log.d("TAG",position+"");
                            if(position==0){
                                toActivity(RoomDetailsActivity.class);
                            }else if(position==1){
                                ToastUtil.showS(mContext,"举报成功!");
                            }else{
                                finish();
                                onBackPressed();
                            }
                        }
                    });
            // 创建弹出窗口
            // 窗口内容为layoutLeft，里面包含一个ListView
            // 窗口宽度跟tvLeft一样
            WindowManager wm = (WindowManager)this
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int width1=width/3;
            //关闭事件
            popMenu = new PopupWindow(layoutMenu, width1,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_user_live, null);
            popMenu.showAsDropDown(ivRight, 10, 0);
            popMenu.update();
            popMenu.setOnDismissListener(new popupDismissListener());
            popMenu.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popMenu.setTouchable(true); // 设置popupwindow可点击
            popMenu.setOutsideTouchable(true); // 设置popupwindow外部可点击
            popMenu.setFocusable(true); // 获取焦点


            popMenu.setTouchInterceptor(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // 如果点击了popupwindow的外部，popupwindow也会消失
                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        popMenu.dismiss();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
        }

    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < Name.length; i++) {
            Log.d("TAG", "" + Name.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", Name[i]);
            dataList.add(map);
        }
        return dataList;
    }

}
