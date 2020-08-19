package com.template.module_common.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;


public class MetaDataUtils {
    public final static String WECHAT_APPID = "WECHAT_APPID";
    public final static String WECHAT_APPKEY = "WECHAT_APPKEY";
    public final static String QQ_APPID = "QQ_APPID";
    public final static String QQ_APPKEY = "QQ_APPKEY";

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    public static String getAppMetaData(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.getContext().getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    Bundle metaData = applicationInfo.metaData;
                    if (metaData != null) {
                        channelNumber = metaData.getString(key);
                        if (channelNumber.startsWith("wk_")) {
                            channelNumber = channelNumber.substring(5);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

}
