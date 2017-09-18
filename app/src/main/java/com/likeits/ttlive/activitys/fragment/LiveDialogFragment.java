package com.likeits.ttlive.activitys.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.event.MessageEvent;
import com.likeits.ttlive.activitys.event.MessageEvent01;
import com.likeits.ttlive.activitys.model.LiveDialogModel;
import com.likeits.ttlive.activitys.ui.live.LiveUserListActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveDialogFragment extends DialogFragment implements View.OnClickListener {


    private TextView tvGoUp;
    private TextView tvCloseUp;
    private TextView tvStartUp;
    private String name;
    private String tag;
    private String flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_live_dialog, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
            flag = bundle.getString("flag");
            tag = bundle.getString("tag");
            Log.d("Tag", "Data01: " + name + " Data02: " + tag + "data03:" + flag);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvGoUp = (TextView) view.findViewById(R.id.text01);
        tvCloseUp = (TextView) view.findViewById(R.id.text02);
        tvStartUp = (TextView) view.findViewById(R.id.text03);
        tvGoUp.setOnClickListener(this);
        tvCloseUp.setOnClickListener(this);
        tvStartUp.setOnClickListener(this);
        if ("1".equals(name) && "flag1".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        } else if ("2".equals(name) && "flag2".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        } else if ("3".equals(name) && "flag3".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        } else if ("4".equals(name) && "flag4".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        } else if ("5".equals(name) && "flag5".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        } else if ("6".equals(name) && "flag6".equals(flag)) {
            if ("0".equals(tag)) {
                tvCloseUp.setVisibility(View.VISIBLE);
                tvStartUp.setVisibility(View.GONE);
            } else {
                tvCloseUp.setVisibility(View.GONE);
                tvStartUp.setVisibility(View.VISIBLE);
            }
        }
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
        switch (v.getId()) {
            case R.id.text01:
                Intent intent=new Intent(getActivity(),LiveUserListActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("flag",flag);
                startActivity(intent);
                getDialog().dismiss();
                break;
            case R.id.text02:
                if ("1".equals(name) && "flag1".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "1";
                    flag = "flag1";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("2".equals(name) && "flag2".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "2";
                    flag = "flag2";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("3".equals(name) && "flag3".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "3";
                    flag = "flag3";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("4".equals(name) && "flag4".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "4";
                    flag = "flag4";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("5".equals(name) && "flag5".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "5";
                    flag = "flag5";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("6".equals(name) && "flag6".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "6";
                    flag = "flag6";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                }

                break;
            case R.id.text03:
                if ("1".equals(name) && "flag1".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "1";
                    flag = "flag1";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("2".equals(name) && "flag2".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "2";
                    flag = "flag2";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("3".equals(name) && "flag3".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "3";
                    flag = "flag3";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("4".equals(name) && "flag4".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "4";
                    flag = "flag4";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("5".equals(name) && "flag5".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "5";
                    flag = "flag5";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                } else if ("6".equals(name) && "flag6".equals(flag)) {
                    if ("0".equals(tag)) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    name = "6";
                    flag = "flag6";
                    EventBus.getDefault().post(new MessageEvent01(new LiveDialogModel(name, tag, flag)));
                    getDialog().dismiss();
                }
                break;
        }
    }


}
