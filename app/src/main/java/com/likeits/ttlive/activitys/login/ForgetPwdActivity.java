package com.likeits.ttlive.activitys.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.Container;
import com.likeits.ttlive.activitys.utils.StringUtil;
import com.likeits.ttlive.activitys.utils.ToastUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

public class ForgetPwdActivity extends Container {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.forget_pwd_et_phone)
    EditText edPhone;
    @BindView(R.id.forget_pwd_et_code)
    EditText edCode;
    @BindView(R.id.forget_pwd_et_pwd)
    EditText edPwd;
    @BindView(R.id.forget_pwd_et_confirm_pwd)
    EditText edConfirmPwd;
    @BindView(R.id.send_code_btn)
    TextView tvSendCode;
    @BindView(R.id.forget_pwd_btn)
    TextView tvRegister;
    @BindView(R.id.web_layout01)
    LinearLayout llLogin;
    private String phone, code, pwd, confirmPwd;
    TimeCount time = new TimeCount(60000, 1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeader.setText("忘记密码");
    }

    @OnClick({R.id.send_code_btn, R.id.forget_pwd_btn, R.id.web_layout01,R.id.backBtn})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.send_code_btn:
                sendCode();
                break;
            case R.id.forget_pwd_btn:
                commit() ;
                break;
            case R.id.web_layout01:
                toActivityFinish(LoginActivity.class);
                break;
        }
    }

    private void sendCode() {
        phone = edPhone.getText().toString().trim();
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

    private void commit() {
        phone = edPhone.getText().toString().trim();
        code = edCode.getTag().toString().trim();
        pwd = edPwd.getText().toString().trim();
        confirmPwd = edConfirmPwd.getText().toString().trim();

        if (StringUtil.isBlank(code)) {
            ToastUtil.showS(mContext, "验证码不能为空");
            return;
        }
        if (StringUtil.isBlank(pwd) || StringUtil.isBlank(confirmPwd)) {
            ToastUtil.showS(mContext, "密码不能为空");
            return;
        }
        SMSSDK.submitVerificationCode("86", phone, code);
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
                            Commit();
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
    private void Commit() {
        ToastUtil.showS(mContext, "修改成功");}
}


