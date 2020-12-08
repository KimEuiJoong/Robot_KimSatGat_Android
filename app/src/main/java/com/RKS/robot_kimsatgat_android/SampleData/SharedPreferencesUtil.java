package com.RKS.robot_kimsatgat_android.SampleData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPreferencesUtil {

    private SharedPreferences pref;
    private Context mContext;
    private static final String Question_Check = "Question_Check";

    public SharedPreferencesUtil(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean getSharedBoolean (String key) {
        pref = mContext.getSharedPreferences(Question_Check, Activity.MODE_PRIVATE);
        Boolean check = pref.getBoolean(key,false);
        return check;
    }

    public void setSharedBoolean (String key) {
        pref = mContext.getSharedPreferences(Question_Check, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key,true);
        editor.commit();
    }
    public void setSharedString(String key, String value){
        pref = mContext.getSharedPreferences(Question_Check, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public String getSharedString(String key){
        pref = mContext.getSharedPreferences(Question_Check, Activity.MODE_PRIVATE);
        return pref.getString(key,null);
    }
    public boolean contains(String key){
        pref = mContext.getSharedPreferences(Question_Check, Activity.MODE_PRIVATE);
        return pref.contains(key);
    }

    public String setKey () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String key = format.format(new Date());
        return key;
    }

}
