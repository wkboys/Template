package com.template.module_common.utils;

import android.os.Bundle;
import com.alibaba.android.arouter.launcher.ARouter;
import com.template.module_common.bean.DemoData;

import java.util.List;

public class ARouterUtils {

    public static void startActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }


    public static void startActivity(String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    public static boolean isLogin() {
//        if (!UserCache.getInstance().isLogin()) {
//            startLoginActivity();
//            return true;
//        } else {
            return false;
//        }
    }

    public static void startLoginActivity() {
//        ARouter.getInstance().build(Constants.ME_LOGIN_BY_PHONE).navigation();
    }

    public static void startDetailActivity(DemoData itemBean) {
//        HistoryCache.getInstance().addHistory(itemBean);
        if (DoubleClickUtils.isDoubleClick()) {
            return;
        }
    }

    public static String list2str(List<String> pic) {
        String pics = "";
        for (String str : pic) {
            pics += str + ",";
        }
        return pics.substring(0, pics.length() - 1);
    }
}
