package br.com.michelbarbosa.hearthstonedusthelperlite.utils;

import android.text.TextUtils;

import java.util.Locale;

public class Util {

    public static String getCurrentLauguage(){
        String mCurrentLanguage = Locale.getDefault().getLanguage();
        return mCurrentLanguage;
    }

    public static String checkNullString(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s;
    }

}
