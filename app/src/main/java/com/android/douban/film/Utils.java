package com.android.douban.film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    public static void parseJSON(String jsonData, ArrayList datas) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.optJSONArray("subjects");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                HashMap map = new HashMap();
                map.put("name", jsonobject.getString("title"));
                map.put("img", jsonobject.getString("cover"));
                map.put("id", String.valueOf(jsonobject.getString("id")));
                datas.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}