package com.template.module_common.utils;


public class DoubleClickUtils {

    private static long lastClickTime;

    private final static int SPACE_TIME = 2000;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
//        Logger.d("space_time " + (currentTime - lastClickTime));
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
            lastClickTime = currentTime;
        } else {
            isClick2 = true;
        }
        return isClick2;
    }

}
