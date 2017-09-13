package com.likeits.ttlive.activitys.fragment;


import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.ui.home.MakeFriendsActivity;
import com.likeits.ttlive.activitys.view.MyListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeFriendsFragment extends DialogFragment implements View.OnClickListener {


    private ImageView ivClose;
    private MyListview mListview;
    private List<Map<String, Object>> dataList;
    // 图片封装为一个数组
    private String[] name = {"热门", "相亲", "男女模", "速配", "音乐"};
    private SimpleAdapter simpleAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_make_friends, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ivClose = (ImageView) view.findViewById(R.id.dialog_iv_close);
        ivClose.setOnClickListener(this);
        mListview = (MyListview) view.findViewById(R.id.home_dialog_listview);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"name"};
        int[] to = {R.id.dialog_tv_name};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.home_dialog_listview_items, from, to);
        //配置适配器
        mListview.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MakeFriendsActivity.class);
                startActivity(intent);
            }
        });
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

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < name.length; i++) {
            Log.d("TAG", "" + name.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onClick(View v) {
        getDialog().dismiss();
    }
}
