package me.kiuber.base.utils;

import java.util.HashSet;
import java.util.List;

/**
 * Created 2017/5/17 0017 21:21
 * Author Kiuber
 * Description
 */

public class SetUtil {
    private static SetUtil instance;

    public static SetUtil get() {
        if (instance == null) {
            instance = new SetUtil();
        }
        return instance;
    }

    public String[] toArray(HashSet<String> permissions) {
        String[] strings = permissions.toArray(new String[permissions.size()]);
//        Iterator<String> iterator = permissions.iterator();
//        int i = 0;
//        while (iterator.hasNext()) {
//            strings[i] = iterator.next();
//            LogUtil.d("strings[i]", strings[i]);
//            i++;
//        }
        return strings;
    }

}
