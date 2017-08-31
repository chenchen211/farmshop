package com.hnxy.farmshop.contract;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.http.HttpResult;

/**
 * Created by Administrator on 2017/7/25.
 */

public interface UploadContract {
    interface Model {
        void upload(String filePath, HttpResult<BaseBean> result);
    }

    interface View {
        void tip(String tip);
    }

    interface Presenter {
        /**
         * 上传图片
         * @param fielPath 选择的文件路径
         */
        void upload(String fielPath);
    }
}
