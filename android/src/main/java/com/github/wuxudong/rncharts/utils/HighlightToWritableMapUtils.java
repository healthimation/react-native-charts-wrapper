package com.github.wuxudong.rncharts.utils;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class HighlightToWritableMapUtils {
    private static final String FIELD_X = "x";
    private static final String FIELD_Y = "y";
    private static final String FIELD_X_PX = "xPx";
    private static final String FIELD_Y_PX = "yPx";
    private static final String FIELD_DRAW_X = "drawX";
    private static final String FIELD_DRAW_Y = "drawY";
    private static final String FIELD_DATA_INDEX = "dataIndex";
    private static final String FIELD_DATA_SET_INDEX = "dataSetIndex";
    private static final String FIELD_STACK_INDEX = "stackIndex";
    private static final String FIELD_AXIS = "axis";
    private static final String FIELD_IS_STACKED = "isStacked";
    private static final String AXIS_LEFT = "LEFT";
    private static final String AXIS_RIGHT = "RIGHT";


    public static WritableMap convertHighlightToWritableMap(Highlight h) {
        if(h == null) {
            return null;
        }

        WritableMap map = new WritableNativeMap();
        map.putDouble(FIELD_X, h.getX());
        map.putDouble(FIELD_Y, h.getY());
        map.putDouble(FIELD_X_PX, h.getXPx());
        map.putDouble(FIELD_Y_PX, h.getYPx());
        map.putDouble(FIELD_DRAW_X, h.getDrawX());
        map.putDouble(FIELD_DRAW_Y, h.getDrawY());
        map.putDouble(FIELD_DRAW_X, h.getDrawX());
        map.putInt(FIELD_DATA_INDEX, h.getDataIndex());
        map.putInt(FIELD_DATA_SET_INDEX, h.getDataSetIndex());
        map.putInt(FIELD_STACK_INDEX, h.getStackIndex());
        map.putString(FIELD_AXIS, h.getAxis() == YAxis.AxisDependency.LEFT ? AXIS_LEFT : AXIS_RIGHT);
        map.putBoolean(FIELD_IS_STACKED, h.isStacked());
        return map;
    }

    private static WritableMap convertMapToWritableMap(Map map) {
        return convertJsonToWritableMap(new JSONObject(map));
    }

    private static WritableMap convertJsonToWritableMap(JSONObject jsonObject) {
        if(jsonObject == null) {
            return null;
        }

        WritableMap map = new WritableNativeMap();

        try {
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    map.putMap(key, convertJsonToWritableMap((JSONObject) value));
                } else if (value instanceof JSONArray) {
                    map.putArray(key, convertJsonToWritableArray((JSONArray) value));
                } else if (value instanceof  Boolean) {
                    map.putBoolean(key, (Boolean) value);
                } else if (value instanceof  Integer) {
                    map.putInt(key, (Integer) value);
                } else if (value instanceof  Double) {
                    map.putDouble(key, (Double) value);
                } else if (value instanceof String)  {
                    map.putString(key, (String) value);
                } else {
                    map.putString(key, value.toString());
                }
            }
        } catch(JSONException ex) {
            map.putString("error", "Failed to convert JSONObject to WritableMap: " + ex.getMessage());
        }

        return map;
    }

    private static WritableArray convertJsonToWritableArray(JSONArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                array.pushMap(convertJsonToWritableMap((JSONObject) value));
            } else if (value instanceof  JSONArray) {
                array.pushArray(convertJsonToWritableArray((JSONArray) value));
            } else if (value instanceof  Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof  Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof  Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof String)  {
                array.pushString((String) value);
            } else {
                array.pushString(value.toString());
            }
        }
        return array;
    }
}
