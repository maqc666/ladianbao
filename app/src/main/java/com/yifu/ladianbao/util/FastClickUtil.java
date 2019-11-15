package com.yifu.ladianbao.util;

public class FastClickUtil {
    public static long lastClickTime;

    //是否是多次点击
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
