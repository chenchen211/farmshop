package com.hnxy.farmshop.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hnxy.farmshop.R;

/**
 * Created by Administrator on 2017/8/29.
 * 将时间戳以时分秒形式显示出来，并且支持倒计时
 */

public class TimerView extends View {


    private int bgColor;
    private int bgImg;
    private boolean isHindBackground;
    private boolean isRun;
    private int mEndStyle;
    private long mTime;

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerView);
        isHindBackground = typedArray.getBoolean(R.styleable.TimerView_isHindBackground, false);
        isRun = typedArray.getBoolean(R.styleable.TimerView_isRun, false);
        mEndStyle = typedArray.getInt(R.styleable.TimerView_endStyle,0);
        bgImg = typedArray.getInteger(R.styleable.TimerView_bgImage, R.mipmap.ic_launcher);
        bgColor = typedArray.getColor(R.styleable.TimerView_bgColor, Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
