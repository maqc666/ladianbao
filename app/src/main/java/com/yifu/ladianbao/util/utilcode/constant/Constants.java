package com.yifu.ladianbao.util.utilcode.constant;


import android.os.Environment;


import com.yifu.ladianbao.R;

import java.io.File;



public class Constants {
    public static final String WX_APP_ID = "wx30843d59f81cda6a";
    public static final String QQ_APP_ID = "101519917";
    public static final int REQUEST_REFRESH = 2018;
    public static final int RESULT_REFRESH = 2019;
    public static final long CODE_TIME = 120000;
    public static final int BUFFER_TIME = 30;
    public static final int COUNT_SIZE = 10;
    public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().toString() + "/LNKJ/images/";
    public static final int COMPRESS_INGNORE = 60;
    public static final String USERINFO = "userInfo";
    public static final int PAYSUCESS = 0x0001;

    public static final String PICTURE_SAVE_PATH = Environment.getExternalStorageDirectory().toString()+ File.separator + R.string.app_name;
    public static final String PICTURE_SAVE_PATH_FILE = Environment.getExternalStorageDirectory().toString()+ File.separator + R.string.app_name+"/File";
    public static final String PICTURE_SAVE_PATH_IMAGE = Environment.getExternalStorageDirectory().toString()+ File.separator + R.string.app_name+"/Image";
}
