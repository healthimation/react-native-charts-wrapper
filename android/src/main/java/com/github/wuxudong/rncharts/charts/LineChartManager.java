package com.github.wuxudong.rncharts.charts;


import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.GroupLineHighlighter;
import com.github.mikephil.charting.highlight.ChartHighlighter;

import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.LineDataExtract;
import com.github.wuxudong.rncharts.listener.RNAccessibilityPerformActions;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;

public class LineChartManager extends BarLineChartBaseManager<LineChart, Entry> {

    @Override
    public String getName() {
        return "RNLineChart";
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        LineChart lineChart =  new LineChart(reactContext);
        lineChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(lineChart));
        lineChart.setOnChartGestureListener(new RNOnChartGestureListener(lineChart));
        lineChart.setAccessibilityPerformActions(new RNAccessibilityPerformActions(lineChart));
        return lineChart;
    }

    @ReactProp(name = "enableGroupHighlighter")
    public void enableGroupLineHighlighter(LineChart chart, boolean enabled) {
        chart.setGroupSelectionEnabled(enabled);
    }

    @Override
    DataExtract getDataExtract() {
        return new LineDataExtract();
    }
}
