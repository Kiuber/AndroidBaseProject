package me.kiuber.base.utils;

import java.util.List;

/**
 * Created 2017/5/17 0017 20:49
 * Author Kiuber
 * Description
 */

public class ListUtil {
    private static ListUtil instance;
    private String DEFAULT_JOIN_SEPARATOR = ",";

    public static ListUtil get() {
        if (instance == null) {
            instance = new ListUtil();
        }
        return instance;
    }

    /**
     * List<String>拼接成字符串，采用默认分隔符
     * @param strings
     * @return
     */
    public String join(List<String> strings) {
        String result = "";
        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 == strings.size()) {
                result += strings.get(i);
            } else {
                result += strings.get(i) + DEFAULT_JOIN_SEPARATOR;
            }
        }
        return result;
    }

    /**
     * List<String>拼接成字符串，指定分分隔符
     * @param strings
     * @param separator
     * @return
     */
    public String join(List<String> strings, String separator) {
        String result = "";
        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 == strings.size()) {
                result += strings.get(i);
            } else {
                result += strings.get(i) + separator;
            }
        }
        return result;
    }

    public String[] toArray(List<String> strings) {
        String[] strings1 = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            String msg = strings.get(i);
            strings1[i] = msg;
        }
        return strings1;
    }
}
