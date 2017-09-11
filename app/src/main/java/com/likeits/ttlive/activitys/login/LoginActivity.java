package com.likeits.ttlive.activitys.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.BaseActivity;
import com.likeits.ttlive.activitys.base.MainActivity;
import com.likeits.ttlive.activitys.utils.MyActivityManager;
import com.likeits.ttlive.activitys.utils.StringUtil;
import com.likeits.ttlive.activitys.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_et_phone)
    EditText edPhone;
    @BindView(R.id.login_et_pwd)
    EditText edpwd;
    @BindView(R.id.login_tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.login_tv_register)
    TextView tvRegister;
    @BindView(R.id.login_tv_login)
    TextView tvLogin;
    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        phone="13515547412";
        pwd="wwwwww";
        edPhone.setText(phone);
        edpwd.setText(pwd);
    }

    @OnClick({R.id.login_tv_forget_pwd, R.id.login_tv_register, R.id.login_tv_login})

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv_login:
                login();

                break;
            case R.id.login_tv_register:
                toActivityFinish(RegisterActivity.class);
                break;
            case R.id.login_tv_forget_pwd:
                toActivity(ForgetPwdActivity.class);
                break;
        }
    }

    private void login() {
        phone = edPhone.getText().toString().trim();
        pwd = edpwd.getText().toString().trim();
        if (StringUtil.isBlank(phone)) {
            ToastUtil.showS(mContext, "手机号不能为空");
            return;
        }
        if (!StringUtil.isCellPhone(phone)) {
            ToastUtil.showS(mContext, "请输入正确的手机号");
            return;
        }
        if (StringUtil.isBlank(pwd)) {
            ToastUtil.showS(mContext, "密码不能为空");
            return;
        }

        toActivityFinish(MainActivity.class);
    }
}
