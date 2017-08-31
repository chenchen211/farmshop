package com.hnxy.farmshop.bean;

import android.app.Activity;

/**
 * Created by Administrator on 2017/7/25.
 */

public class Classify {
    private String text;
    private Class<Activity> target;

    public Classify(String text, Class<Activity> target) {
        this.text = text;
        this.target = target;
    }

    public Classify(String text) {
        this.text = text;
    }

    public Class<Activity> getTarget() {
        return target;
    }

    public void setTarget(Class<Activity> target) {
        this.target = target;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
