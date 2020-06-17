package utils;

import com.google.gson.Gson;

import java.util.List;

public class jsonUtils {
    public static String listToJson(List<?> list) {
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }
}
