package com.template.module_common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 对SharedPreference文件中的各种类型的数据进行存取操作
 */
public class SPUtils {

    private static SharedPreferences sp;

    private static void init(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(Utils.getContext());
        }
    }

    public static void setSharedIntData(Context context, String key, int value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putInt(key, value).commit();
    }

    public static int getSharedIntData(Context context, String key, int defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getInt(key, defValue);
    }

    public static void setSharedlongData(Context context, String key, long value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putLong(key, value).commit();
    }

    public static long getSharedlongData(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getLong(key, 0l);
    }

    public static void setSharedFloatData(Context context, String key,
                                          float value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putFloat(key, value).commit();
    }

    public static Float getSharedFloatData(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getFloat(key, 0f);
    }

    public static void setSharedBooleanData(Context context, String key,
                                            boolean value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getSharedBooleanData(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getBoolean(key, false);
    }

    public static void setSharedStringData(Context context, String key, String value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getSharedStringData(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getString(key, "");
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(Context context, String tag, List<T> datalist) {
        if (sp == null) {
            init(context);
        }
        if (null == datalist || datalist.size() <= 0) {
            sp.edit().clear().commit();
            return;
        }
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        sp.edit().putString(tag, strJson).commit();

    }


    public static <K, V> void setMap(Context context, String tag, Map<K, V> map) {
        if (sp == null) {
            init(context);
        }
        if (null == map || map.size() <= 0) {
            sp.edit().clear().commit();
            return;
        }
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(map);
        sp.edit().putString(tag, strJson).commit();
    }


    public static <K, V> Map<K, V> getLinkedHashMap(Context context, String tag) {
        Map<K, V> map = new LinkedHashMap<>();
        if (sp == null) {
            init(context);
        }
        String strJson = sp.getString(tag, null);
        if (TextUtils.isEmpty(strJson)) {
            return map;
        }
        Gson gson = new Gson();
        map = gson.fromJson(strJson, map.getClass());
        return map;
    }


    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(Context context, String tag) {
        return getDataList(context, tag, String.class);
    }

    public static <T> List<T> getDataList(Context context, String tag, Class tClass) {
        List<T> datalist = new ArrayList<T>();
        if (sp == null) {
            init(context);
        }
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new Gson2ListParameterizedType(tClass));
        return datalist;
    }

    public String[] getSharedPreference(Context context, String key) {
        String regularEx = "#";
        String[] str = null;
        if (sp == null) {
            init(context);
        }
        String values;
        values = sp.getString(key, "");
        str = values.split(regularEx);

        return str;
    }

    public void setSharedPreference(Context context, String key, String[] values) {
        String regularEx = "#";
        String str = "";
        if (sp == null) {
            init(context);
        }
        if (values != null && values.length > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            sp.edit().putString(key, str).commit();
        }
    }

}