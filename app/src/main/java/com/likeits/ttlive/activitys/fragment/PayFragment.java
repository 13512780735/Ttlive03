package com.likeits.ttlive.activitys.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.likeits.ttlive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends DialogFragment implements View.OnClickListener {
    private ImageView ivClose;
    private String data01, data02;
    private TextView tvRubyNumber, tvAccount, tvCash;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            data01 = bundle.getString("Data01");
            data02 = bundle.getString("Data02");
            Log.d("Tag", "Data01: " + data01 + " Data02: " + data02);
        }
        initView(view);
        return view;

    }

    private void initView(View view) {
        ivClose = (ImageView) view.findViewById(R.id.pay_dialog_iv_close);
        tvRubyNumber = (TextView) view.findViewById(R.id.pay_dialog_tv_rubyNumber);
        tvAccount = (TextView) view.findViewById(R.id.pay_dialog_tv_account);
        tvCash = (TextView) view.findViewById(R.id.pay_dialog_tv_cash);
        ivClose.setOnClickListener(this);
        tvRubyNumber.setText("红宝石 x "+data02);
        tvCash.setText("¥ "+data01+"元");
        tvAccount.setText("(正在为账号：13515547412购买)");
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;

        window.setAttributes(windowParams);
    }


    @Override
    public void onClick(View v) {
        getDialog().dismiss();
    }
}
