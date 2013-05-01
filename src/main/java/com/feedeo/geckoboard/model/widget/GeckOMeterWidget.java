package com.feedeo.geckoboard.model.widget;

import com.feedeo.geckoboard.model.ValueWithText;

public class GeckOMeterWidget extends GeckoboardWidget {
    private Number item;
    private ValueWithText max;
    private ValueWithText min;

    public Number getItem() {
        return item;
    }

    public ValueWithText getMax() {
        return max;
    }

    public ValueWithText getMin() {
        return min;
    }

    public GeckOMeterWidget(Number currentValue,
                            Number minValue, String minDescription,
                            Number maxValue, String maxDescription) {

        item = currentValue;
        max = new ValueWithText(minValue, minDescription);
        min = new ValueWithText(maxValue, maxDescription);
    }
}
