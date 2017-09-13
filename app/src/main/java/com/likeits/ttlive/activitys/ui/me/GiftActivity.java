package com.likeits.ttlive.activitys.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GiftActivity  extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView> {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.gift_scrollview)
    PullToRefreshScrollView mPullToRefreshScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("礼品兑换");
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
    }

    @OnClick({R.id.backBtn,R.id.gift_rl_earnings,R.id.gift_rl_break_even,R.id.gift_rl_withdraw,R.id.gift_rl_exchange_gift,R.id.gift_rl_monthly_statement})
    public void Onclick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                onBackPressed();
                break;
            /**
             * 收益记录
             */
            case R.id.gift_rl_earnings:
              toActivity(EarningsActivity.class);
                break;
            /**
             * 礼物收支
             */
            case R.id.gift_rl_break_even:
                toActivity(BreakEvenActivity.class);
                break;
            /**
             * 月结记录
             */
            case R.id.gift_rl_monthly_statement:
                toActivity(MonthlyStatementActivity.class);
                break;
            /**
             * 提现礼品
             */
            case R.id.gift_rl_withdraw:
              toActivity(WithdrawActivity.class);
                break;
            /**
             * 兑换礼品
             */
            case R.id.gift_rl_exchange_gift:
                toActivity(ExchangeGiftActivity.class);
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
