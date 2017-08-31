package com.hnxy.farmshop.util;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017/8/2.
 */

public class SmsObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    private ContentResolver mResolver;
    public Handler smsHandler;
    private Uri SMS_INBOX = Uri.parse("content://sms/");

    public SmsObserver(ContentResolver mResolver, Handler handler) {
        super(handler);
        this.mResolver = mResolver;
        this.smsHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        Cursor mCursor = mResolver.query(SMS_INBOX, new String[] { "_id", "address", "read", "body", "thread_id" },
                "read=?", new String[] { "0" }, "date desc");

        if (mCursor == null) {
            return;
        } else {
            while (mCursor.moveToNext()) {
                SmsInfo _smsInfo = new SmsInfo();

                int _inIndex = mCursor.getColumnIndex("_id");
                if (_inIndex != -1) {
                    _smsInfo._id = mCursor.getString(_inIndex);
                }

                int thread_idIndex = mCursor.getColumnIndex("thread_id");
                if (thread_idIndex != -1) {
                    _smsInfo.thread_id = mCursor.getString(thread_idIndex);
                }

                int addressIndex = mCursor.getColumnIndex("address");
                if (addressIndex != -1) {
                    _smsInfo.smsAddress = mCursor.getString(addressIndex);
                }

                int bodyIndex = mCursor.getColumnIndex("body");
                if (bodyIndex != -1) {
                    _smsInfo.smsBody = mCursor.getString(bodyIndex);
                }

                int readIndex = mCursor.getColumnIndex("read");
                if (readIndex != -1) {
                    _smsInfo.read = mCursor.getString(readIndex);
                }

                // 根据你的拦截策略，判断是否不对短信进行操作;将短信设置为已读;将短信删除
                Message msg = smsHandler.obtainMessage();
                _smsInfo.action = 2;// 0不对短信进行操作;1将短信设置为已读;2将短信删除
                msg.obj = _smsInfo;
                smsHandler.sendMessage(msg);
            }
            mCursor.close();
        }
    }
}
