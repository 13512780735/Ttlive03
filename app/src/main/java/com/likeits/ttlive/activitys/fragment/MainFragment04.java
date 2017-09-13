package com.likeits.ttlive.activitys.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.ui.me.ApplyErederActivity;
import com.likeits.ttlive.activitys.ui.me.ApplyShortActivity;
import com.likeits.ttlive.activitys.ui.me.AttentionActivity;
import com.likeits.ttlive.activitys.ui.me.DviseActivity;
import com.likeits.ttlive.activitys.ui.me.ExplainActivity;
import com.likeits.ttlive.activitys.ui.me.GiftActivity;
import com.likeits.ttlive.activitys.ui.me.MemberActivity;
import com.likeits.ttlive.activitys.ui.me.MountActivity;
import com.likeits.ttlive.activitys.ui.me.PayActivity;
import com.likeits.ttlive.activitys.ui.me.RoomActivity;
import com.likeits.ttlive.activitys.ui.me.SettingActivity;
import com.likeits.ttlive.activitys.ui.me.TaskActivity;
import com.likeits.ttlive.activitys.ui.me.UserCentreActivity;
import com.likeits.ttlive.activitys.view.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment04 extends MyBaseFragment implements
        PullToRefreshBase.OnRefreshListener2<ScrollView>, View.OnClickListener {


    private TextView tvHeader;
    private Button btBack;
    private CircleImageView ivAvatar;
    private TextView tvName, tvNumber, tvContent;
    private PullToRefreshScrollView mPullToRefreshScrollView;
    private RelativeLayout rlRoom, rlPay, rlAttention, rlGift, rlMount, rlMember, rlApplyShort, rlApplyEreder, rlTask, rlExplain, rlDvise, rlSetting;
    private LinearLayout llUserData;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_fragment04;
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
        tvHeader.setText("我");
        mPullToRefreshScrollView = findViewById(R.id.ll_me_scrollview);
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
        ivAvatar = findViewById(R.id.me_header_avatar);
        tvName = findViewById(R.id.me_header_name);
        tvNumber = findViewById(R.id.me_header_number);
        tvContent = findViewById(R.id.home_listview_content);//年齡，地區
        llUserData = findViewById(R.id.me_header_userData);//用戶資料
        rlRoom = findViewById(R.id.me_gridView_rl_room);//房间
        rlPay = findViewById(R.id.me_gridView_rl_pay);//充值
        rlAttention = findViewById(R.id.me_gridView_rl_attention);//关注
        rlGift = findViewById(R.id.me_gridView_rl_gift);//礼品兑换
        rlMount = findViewById(R.id.me_gridView_rl_mount);//购买坐骑
        rlMember = findViewById(R.id.me_gridView_rl_member);//购买会员
        rlApplyShort = findViewById(R.id.me_gridView_rl_apply_short);//申请短位
        rlApplyEreder = findViewById(R.id.me_gridView_rl_apply_eredar);//申请达人
        rlTask = findViewById(R.id.me_gridView_rl_task);//奖励任务
        rlExplain = findViewById(R.id.me_gridView_rl_explain);//玩法说明
        rlDvise = findViewById(R.id.me_gridView_rl_dvise);//投诉建议
        rlSetting = findViewById(R.id.me_gridView_rl_setting);//设置

    }

    private void initListener() {
        llUserData.setOnClickListener(this);
        rlRoom.setOnClickListener(this);
        rlPay.setOnClickListener(this);
        rlAttention.setOnClickListener(this);
        rlGift.setOnClickListener(this);
        rlMount.setOnClickListener(this);
        rlMember.setOnClickListener(this);
        rlApplyShort.setOnClickListener(this);
        rlApplyEreder.setOnClickListener(this);
        rlTask.setOnClickListener(this);
        rlExplain.setOnClickListener(this);
        rlDvise.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        mPullToRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_header_userData:
                toActivity(UserCentreActivity.class);
                break;
            case R.id.me_gridView_rl_room:
                toActivity(RoomActivity.class);
                break;
            case R.id.me_gridView_rl_pay:
                toActivity(PayActivity.class);
                break;
            case R.id.me_gridView_rl_attention:
                toActivity(AttentionActivity.class);
                break;
            case R.id.me_gridView_rl_gift:
                toActivity(GiftActivity.class);
                break;
            case R.id.me_gridView_rl_mount:
                toActivity(MountActivity.class);
                break;
            case R.id.me_gridView_rl_member:
                toActivity(MemberActivity.class);
                break;
            case R.id.me_gridView_rl_apply_short:
                toActivity(ApplyShortActivity.class);
                break;
            case R.id.me_gridView_rl_apply_eredar:
                toActivity(ApplyErederActivity.class);
                break;
            case R.id.me_gridView_rl_task:
                toActivity(TaskActivity.class);
                break;
            case R.id.me_gridView_rl_explain:
                toActivity(ExplainActivity.class);
                break;
            case R.id.me_gridView_rl_dvise:
                toActivity(DviseActivity.class);
                break;
            case R.id.me_gridView_rl_setting:
                toActivity(SettingActivity.class);
                break;


        }
    }
}
