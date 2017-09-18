package com.likeits.ttlive.activitys.event;

import com.likeits.ttlive.activitys.model.LiveDialogModel;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MessageEvent01 {
    private LiveDialogModel mLiveDialogModel;

    public MessageEvent01(LiveDialogModel liveDialogModel) {
        this.mLiveDialogModel = liveDialogModel;
    }

    public LiveDialogModel getmLiveDialogModel() {
        return mLiveDialogModel;
    }

    public void setmLiveDialogModel(LiveDialogModel mLiveDialogModel) {
        this.mLiveDialogModel = mLiveDialogModel;
    }
}
