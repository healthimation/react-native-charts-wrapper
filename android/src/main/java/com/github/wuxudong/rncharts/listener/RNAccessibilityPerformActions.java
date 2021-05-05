package com.github.wuxudong.rncharts.listener;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.accessibility.AccessibilityPerformActions;
import com.github.mikephil.charting.charts.Chart;

import java.lang.ref.WeakReference;

public class RNAccessibilityPerformActions implements AccessibilityPerformActions {

    private WeakReference<Chart> mWeakChart;

    public RNAccessibilityPerformActions(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    @Override
    public void clearAccessibilityFocus(int virtualViewId, int entryCount) {
        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();
            WritableMap map = new WritableNativeMap();
            map.putString("virtualViewId", String.valueOf(virtualViewId));
            map.putString("entryCount", String.valueOf(entryCount));

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "clearAccessibilityFocus",
                    map);
        }
    }

}
