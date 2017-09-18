package com.likeits.ttlive.activitys.model;

/**
 * Created by Administrator on 2017/9/18.
 */

public class LiveDialogModel {
    public String name;
    public String tag;
    public String flag;

    public LiveDialogModel(String name, String tag, String flag) {
        this.name = name;
        this.tag = tag;
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
