package com.likeits.ttlive.activitys.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.likeits.ttlive.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Iterator;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

public class MyApplication extends Application {

    public static MyApplication mContext;
    private static MyApplication instance;
    //  private UserInfo userInfo = null;
    // 记录是否已经初始化
    private boolean isInit = false;
    public final String PREF_USERNAME = "username";
    public static String currentUserNick = "";

    public static MyApplication getInstance() {
        if (mContext == null) {
            return new MyApplication();
        } else {
            return mContext;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
        //  DemoHelper.getInstance().init(mContext);
        //  initRedPacket();
        initMob();
        // 初始化环信SDK
        //initEasemob();
        // 图片加载工具初始化
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_public_nophoto)
                .showImageOnFail(R.mipmap.ic_public_nophoto)
                .cacheInMemory(true).cacheOnDisc(true).build();
        // 图片加载工具配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
//        //初始化
//       Beta.autoCheckUpgrade = true;//设置自动检查
//        Bugly.init(mContext, "6441c3da7a", false);
    }

//    private void initRedPacket() {
//        //red packet code : 初始化红包SDK，开启日志输出开关
//        RedPacket.getInstance().initRedPacket(mContext, RPConstant.AUTH_METHOD_EASEMOB, new RPInitRedPacketCallback() {
//
//            @Override
//            public void initTokenData(RPValueCallback<TokenData> callback) {
//                TokenData tokenData = new TokenData();
//                tokenData.imUserId = EMClient.getInstance().getCurrentUser();
//                //此处使用环信id代替了appUserId 开发者可传入App的appUserId
//                tokenData.appUserId = EMClient.getInstance().getCurrentUser();
//                tokenData.imToken = EMClient.getInstance().getAccessToken();
//                //同步或异步获取TokenData 获取成功后回调onSuccess()方法
//                callback.onSuccess(tokenData);
//            }
//
//            @Override
//            public RedPacketInfo initCurrentUserSync() {
//                //这里需要同步设置当前用户id、昵称和头像url
//                String fromAvatarUrl = "";
//                String fromNickname = EMClient.getInstance().getCurrentUser();
//                EaseUser easeUser = EaseUserUtils.getUserInfo(fromNickname);
//                if (easeUser != null) {
//                    fromAvatarUrl = TextUtils.isEmpty(easeUser.getAvatar()) ? "none" : easeUser.getAvatar();
//                    fromNickname = TextUtils.isEmpty(easeUser.getNick()) ? easeUser.getUsername() : easeUser.getNick();
//                }
//                RedPacketInfo redPacketInfo = new RedPacketInfo();
//                redPacketInfo.fromUserId = EMClient.getInstance().getCurrentUser();
//                redPacketInfo.fromAvatarUrl = fromAvatarUrl;
//                redPacketInfo.fromNickName = fromNickname;
//                return redPacketInfo;
//            }
//        });
//        RedPacket.getInstance().setDebugMode(true);
//        //end of red packet code
//    }


//    private void initEasemob() {
//        // 获取当前进程 id 并取得进程名
//        int pid = android.os.Process.myPid();
//        String processAppName = getAppName(pid);
//        /**
//         * 如果app启用了远程的service，此application:onCreate会被调用2次
//         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
//         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
//         */
//        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
//            // 则此application的onCreate 是被service 调用的，直接返回
//            return;
//        }
//        if (isInit) {
//            return;
//        }
//
//        //调用初始化方法初始化sdk
//        EMClient.getInstance().init(mContext, initOptions());
//
//        //设置开启debug模式
//        EMClient.getInstance().setDebugMode(true);
//
//        // 设置初始化已经完成
//        isInit = true;
//    }

//    /**
//     * SDK初始化的一些配置
//     * 关于 EMOptions 可以参考官方的 API 文档
//     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
//     */
//    private EMOptions initOptions() {
//
//        EMOptions options = new EMOptions();
//        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
//        // options.setAppKey("lzan13#hxsdkdemo");
//        // 设置自动登录
//        options.setAutoLogin(true);
//        // 设置是否需要发送已读回执
//        options.setRequireAck(true);
//        // 设置是否需要发送回执，
//        options.setRequireDeliveryAck(true);
//        // 设置是否需要服务器收到消息确认
//        // options.setRequireServerAck(true);
//        // 设置是否根据服务器时间排序，默认是true
//        options.setSortMessageByServerTime(false);
//        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
//        options.setAcceptInvitationAlways(false);
//        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
//        options.setAutoAcceptGroupInvitation(false);
//        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
//        options.setDeleteMessagesAsExitGroup(false);
//        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
//        options.allowChatroomOwnerLeave(true);
//        // 设置google GCM推送id，国内可以不用设置
//        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
//        // 设置集成小米推送的appid和appkey
//        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);
//
//        return options;
//    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }


    private void initMob() {
        ShareSDK.initSDK(mContext);
        SMSSDK.initSDK(mContext, "20d684bcd53f6", "0591a003565c63f70bdb020607035504");
    }


    public static MyApplication getInstance(Context appContext) {
        return instance;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
//    public UserInfo getUserInfo() {
//        if (userInfo == null)
//            init();
//        return userInfo;
//    }
}
//    private void init() {
//        userInfo = Storage.getObject(AppConfig.USER_INFO, UserInfo.class);
//
//    }

/**
 * 登录信息的保存
 *
 * @param user
 */
//    public static void doLogin(UserInfo user) {
//        Storage.saveObject(AppConfig.USER_INFO, user);
//        Storage.saveObject(AppConfig.USER_INFO, user);
//        Preferences
//                .saveString(AppConfig.USER_ID, user.getUkey(), getInstance());
//        MyApplication.getInstance().init();
//    }

//    /**
//     * 清除登录信息(退出账号)
//     */
//    public static void doLogout() {
//        Storage.clearObject(AppConfig.USER_INFO);
//        MyApplication.getInstance().init();
//    }
//}