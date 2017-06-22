package com.ma.pingan.comprehensive.manger;

import com.ma.pingan.comprehensive.bean.SubEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public class EventManager {

    public static void refreshSubCategory(String minor, String type) {
        EventBus.getDefault().post(new SubEvent(minor, type));
    }
}
