package me.kiuber.base.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 2017/5/17 0017 20:58
 * Author Kiuber
 * Description
 */

public class ArrayUtil {
    private static ArrayUtil instance;

    public static ArrayUtil get() {
        if (instance == null) {
            instance = new ArrayUtil();
        }
        return instance;
    }

    /**
     * 字符串数组转List
     * @param strings
     * @return
     */
    public List<String> toList(String[] strings) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }
}
