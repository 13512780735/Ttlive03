package com.likeits.ttlive.activitys.ui.me;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.utils.BitmapOption;
import com.likeits.ttlive.activitys.utils.StringUtil;
import com.likeits.ttlive.activitys.utils.ToastUtil;
import com.pk4pk.baseappmoudle.utils.FileUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

public class ApplyErederActivity extends Container implements
        PullToRefreshBase.OnRefreshListener2<ScrollView>{
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.apply_et_name)
    EditText etName;
    @BindView(R.id.apply_et_id_card)
    EditText etIdCard;
    @BindView(R.id.apply_et_phone)
    EditText etPhone;
    @BindView(R.id.apply_et_code)
    EditText etCode;
    @BindView(R.id.send_code_btn)
    TextView tvSendCode;
    @BindView(R.id.tv_apply)
    TextView tvApply;
    @BindView(R.id.ll_id)
    LinearLayout ll_id;
    @BindView(R.id.iv_id_card_positive)
    ImageView ivPositive;
    @BindView(R.id.iv_id_card_contrary)
    ImageView ivContrary;
    @BindView(R.id.iv_id_hand_card)
    ImageView ivHandCard;
    @BindView(R.id.apply_scrollView)
    PullToRefreshScrollView mPullToRefreshScrollView;
    TimeCount time = new TimeCount(60000, 1000);
    private String phone;
    private String code;
    private String name;
    private String idCard;
    private String stats;
    private View mpopview;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_ereder);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("申请达人");
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

    @OnClick({R.id.backBtn,R.id.send_code_btn,R.id.tv_apply,R.id.iv_id_card_positive,R.id.iv_id_card_contrary,R.id.iv_id_hand_card})
    public void Onclick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.send_code_btn:
                sendCode();
                break;
            case R.id.tv_apply:
                apply();
                break;
            case R.id.iv_id_card_positive:
                stats="1";
                selectAvatar();
                break;
            case R.id.iv_id_card_contrary:
                stats="2";
                selectAvatar();
                break;
            case R.id.iv_id_hand_card:
                stats="3";
                selectAvatar();
                break;
        }
    }
    private void apply() {
        name= etName.getText().toString().trim();
        idCard=etIdCard.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();

        if (StringUtil.isBlank(name)) {
            ToastUtil.showS(mContext, "姓名不能为空");
            return;
        }
        if (StringUtil.isBlank(idCard)) {
            ToastUtil.showS(mContext, "身份证号不能为空");
            return;
        }
        SMSSDK.submitVerificationCode("86", phone, code);
    }
    private void sendCode() {
        phone = etPhone.getText().toString().trim();
        if (StringUtil.isBlank(phone)) {
            ToastUtil.showS(mContext, "手机号不能为空");
            return;
        }
        if (!(StringUtil.isCellPhone(phone))) {
            ToastUtil.showS(mContext, "请输入正确的手机号码");
            return;
        } else {
            SMSSDK.getVerificationCode("86", phone);
            time.start();
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

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            tvSendCode.setText("获取验证码");
            tvSendCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            tvSendCode.setClickable(false);//防止重复点击
            tvSendCode.setText(millisUntilFinished / 1000 + "s");
        }
    }

    Handler mHandler = new

            Handler() {
                public void handleMessage(Message msg) {

                    // TODO Auto-generated method stub
                    super.handleMessage(msg);
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e("event", "event=" + event);
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        System.out.println("--------result" + event);
                        //短信注册成功后，返回MainActivity,然后提示新好友
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                            //Toast.makeText(getApplicationContext(), "提交验证码成功"+data.toString(), Toast.LENGTH_SHORT).show();
                            Apply();
                            showProgress("Loading...");
                        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            //已经验证
                            Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();


                        }

                    } else {
                        int status = 0;
                        try {
                            ((Throwable) data).printStackTrace();
                            Throwable throwable = (Throwable) data;

                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");
                            status = object.optInt("status");
                            if (!TextUtils.isEmpty(des)) {
                                Toast.makeText(mContext, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            SMSLog.getInstance().w(e);
                        }
                    }


                }
            };
    @SuppressLint("InflateParams")
    private void selectAvatar() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mpopview = inflater.inflate(R.layout.layout_choose_photo, null);
        mPopupWindow = new PopupWindow(mpopview, ll_id.getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.mid_filter_bg));

        //   mPopupWindow.showAsDropDown(ll_id, 0, 20, Gravity.CENTER);
        mPopupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.layout_choose_photo, null), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mPopupWindow.setOutsideTouchable(false);
        //mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setAnimationStyle(R.style.AnimBottom);
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setTouchable(true); // 设置popupwindow可点击
        mPopupWindow.setOutsideTouchable(true); // 设置popupwindow外部可点击
        mPopupWindow.setFocusable(true); // 获取焦点
        mPopupWindow.update();

        Button mbuttonTakePhoto = (Button) mpopview
                .findViewById(R.id.button_take_photo);
        Button mbuttonChoicePhoto = (Button) mpopview
                .findViewById(R.id.button_choice_photo);
        Button mbuttonChoicecannce = (Button) mpopview
                .findViewById(R.id.button_choice_cancer);

        // 相册上传
        mbuttonChoicePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
                startActivityForResult(i, 2);
            }
        });

        // 拍照上传
        mbuttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
                @SuppressWarnings("unused")
                Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                startActivityForResult(intent, 1);
            }
        });

        mbuttonChoicecannce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果点击了popupwindow的外部，popupwindow也会消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
    }
    @SuppressLint("SdCardPath")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) { // 拍照
                Bundle extras = data.getExtras();
                Bitmap photoBit = (Bitmap) extras.get("data");
                Bitmap option = BitmapOption.bitmapOption(photoBit, 5);
                if("1".equals(stats)){
                    ivPositive.setImageBitmap(option);
                }else if("2".equals(stats)){
                    ivContrary.setImageBitmap(option);
                }else{
                    ivHandCard.setImageBitmap(option);
                }
                Log.d("TAG",data.toString());
                saveBitmap2file(option, "0001.jpg");
                final File file = new File("/sdcard/" + "0001.jpg");
                try {
                    String base64Token = Base64.encodeToString(FileUtil.getFileToByte(file), Base64.DEFAULT);//  编码后
                    //upload(base64Token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", "file333333333333333   " + file.getName());
                // 开始联网上传的操作

            } else if (requestCode == 2) { // 相册
                try {
                    Uri uri = data.getData();
                    String[] pojo = { MediaStore.Images.Media.DATA };
                    @SuppressWarnings("deprecation")
                    Cursor cursor = managedQuery(uri, pojo, null, null, null);
                    if (cursor != null) {
                        ContentResolver cr = this.getContentResolver();
                        int colunm_index = cursor
                                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        String path = cursor.getString(colunm_index);
                        final File file = new File(path);
                        Bitmap bitmap = BitmapFactory.decodeStream(cr
                                .openInputStream(uri));
                        Bitmap option = BitmapOption.bitmapOption(bitmap, 5);
                        if("1".equals(stats)){
                            ivPositive.setImageBitmap(option);
                        }else if("2".equals(stats)){
                            ivContrary.setImageBitmap(option);
                        }else{
                            ivHandCard.setImageBitmap(option);
                        }
                        //  iv01.setImageBitmap(option);// 设置为头像的背景
                        Log.e("TAG", "fiels11111  " + file.getName());
                        try {
                            String base64Token = Base64.encodeToString(FileUtil.getFileToByte(file), Base64.DEFAULT);//  编码后
                            // upload(base64Token);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 开始联网上传的操作

                    }
                } catch (Exception e) {

                }
            }
        }

    }
    @SuppressLint("SdCardPath")
    static boolean saveBitmap2file(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream("/sdcard/" + filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }
    private void Apply() {
        ToastUtil.showS(mContext, "申请成功");}
}
