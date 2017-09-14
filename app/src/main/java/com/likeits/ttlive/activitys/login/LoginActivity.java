package com.likeits.ttlive.activitys.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.likeits.ttlive.R;
import com.likeits.ttlive.activitys.base.BaseActivity;
import com.likeits.ttlive.activitys.base.MainActivity;
import com.likeits.ttlive.activitys.utils.MyActivityManager;
import com.likeits.ttlive.activitys.utils.StringUtil;
import com.likeits.ttlive.activitys.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


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
        openPermission();
        phone="13515547412";
        pwd="wwwwww";
        edPhone.setText(phone);
        edpwd.setText(pwd);
    }
    private void openPermission() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE + Manifest.permission.CAMERA+Manifest.permission.WRITE_EXTERNAL_STORAGE
                +Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(mContext,"请授予下面权限",Toast.LENGTH_SHORT).show();
            List<PermissionItem> permissions = new ArrayList<PermissionItem>();
            permissions.add(new PermissionItem(Manifest.permission.CALL_PHONE, "电话", R.drawable.permission_ic_phone));
            permissions.add(new PermissionItem(Manifest.permission.CAMERA, "照相", R.drawable.permission_ic_camera));
            permissions.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE , "储存空间", R.drawable.permission_ic_storage));
            // permissions.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "Camera", R.drawable.permission_ic_storage));
            HiPermission.create(mContext)
                    .permissions(permissions)
                    .msg("为了您正常使用TT直播间应用，需要以下权限")
                    .animStyle(R.style.PermissionAnimModal)
//                        .style(R.style.CusStyle)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            Log.i(TAG, "onClose");
                            ToastUtil.showS(mContext,"权限被拒绝");
                        }

                        @Override
                        public void onFinish() {
                            //ToastUtil.showS(mContext,"权限已被开启");
                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            Log.i(TAG, "onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            Log.i(TAG, "onGuarantee");
                        }
                    });
            return;
        }}

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
