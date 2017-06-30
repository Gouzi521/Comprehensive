package com.ma.pingan.comprehensive.base;

import android.graphics.Color;
import android.support.annotation.StringDef;

import com.ma.pingan.comprehensive.utils.AppUtils;
import com.ma.pingan.comprehensive.utils.FileUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mapingan
 * on 2017/6/21 0021.
 */

public class Constant {


    public static String EXTRA_IMG_URL = "extra_img_url";
    public static String EXTRA_AV = "extra_av";
    public static final String EXTRA_PARTITION = "extra_partition";


    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";

    public static final String API_BASE_URL = "http://api.zhuishushenqi.com";
    public static final String Bili_BASE_URL = "http://app.bilibili.com/";
    public static final int[] tagColors = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    @StringDef({
            Gender.MALE,
            Gender.FEMALE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {
        String MALE = "male";

        String FEMALE = "female";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CateType {
        String HOT = "hot";

        String NEW = "new";

        String REPUTATION = "reputation";

        String OVER = "over";
    }

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";
    public static String PATH_TXT = PATH_DATA + "/book/";
    public static final String SUFFIX_ZIP = ".zip";
    public static String PATH_EPUB = PATH_DATA + "/epub";

    public static final String ISNIGHT = "isNight";
    public static final String FLIP_STYLE = "flipStyle";
    public static String BASE_PATH = AppUtils.getAppContext().getCacheDir().getPath();
}
