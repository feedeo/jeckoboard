package com.feedeo.geckoboard.model.widget;

import com.feedeo.geckoboard.model.Settings;
import com.feedeo.geckoboard.model.ValueWithText;

import java.util.ArrayList;
import java.util.List;

public class LineChartWidget extends GeckoboardWidget {
    private List<String> item;
    private Settings settings;

    public LineChartWidget(List<Number> values, List<String> axysx, List<String> axysy) {
        item = new ArrayList<String>(values.size());
        for (Number value : values) {
            item.add(value.toString());
        }

        settings = new Settings(axysx, axysy, null);
    }

    public LineChartWidget(List<Number> values, List<String> axysx, List<String> axysy, String colour) {
        item = new ArrayList<String>(values.size());
        for (Number value : values) {
            item.add(value.toString());
        }

        settings = new Settings(axysx, axysy, null);
    }

    public List<String> getItem() {
        return item;
    }

    public Settings getSettings() {
        return settings;
    }
}
