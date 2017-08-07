package com.qingmei2.sample_androidtest.normal;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by QingMei on 2017/8/7.
 * desc:
 */

public class Preferences {

    private static final String PREFERENCES_NAME = "Test_Preferences";

    private final SharedPreferences sp;

    public Preferences(Context context) {
        sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setUserName(String userName) {
        sp.edit().putString("username", userName).apply();
    }

    public String getUserName() {
        return sp.getString("username", "");
    }
}
