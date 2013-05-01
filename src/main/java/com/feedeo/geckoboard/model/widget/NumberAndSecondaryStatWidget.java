package com.feedeo.geckoboard.model.widget;

import com.feedeo.geckoboard.model.ValueWithText;

import java.util.ArrayList;
import java.util.List;

public class NumberAndSecondaryStatWidget extends GeckoboardWidget {
    private List<ValueWithText> item;
    private String type;

    public NumberAndSecondaryStatWidget(Number value) {
        this.item = new ArrayList<ValueWithText>(1);
        this.item.add(new ValueWithText(value, ""));
    }

    public NumberAndSecondaryStatWidget(Number value, String text) {
        this.item = new ArrayList<ValueWithText>(1);
        this.item.add(new ValueWithText(value, text));
    }

    public NumberAndSecondaryStatWidget(Number value, Number secondaryStat) {
        this.item = new ArrayList<ValueWithText>(2);
        this.item.add(new ValueWithText(value, ""));
        this.item.add(new ValueWithText(secondaryStat, ""));
    }

    public NumberAndSecondaryStatWidget(Number value, Number secondaryStat, boolean reverseColour) {
        this.item = new ArrayList<ValueWithText>(2);
        this.item.add(new ValueWithText(value, ""));
        this.item.add(new ValueWithText(secondaryStat, ""));

        if (reverseColour) type = "reverse";
    }

    public NumberAndSecondaryStatWidget(Number value, String text, Number secondaryValue, String secondaryText) {
        this.item = new ArrayList<ValueWithText>(2);
        this.item.add(new ValueWithText(value, text));
        this.item.add(new ValueWithText(secondaryValue, secondaryText));
    }

    public NumberAndSecondaryStatWidget(Number value, String text, Number secondaryValue, String secondaryText, boolean reverseColour) {
        this.item = new ArrayList<ValueWithText>(2);
        this.item.add(new ValueWithText(value, text));
        this.item.add(new ValueWithText(secondaryValue, secondaryText));

        if (reverseColour) type = "reverse";
    }

    public List<ValueWithText> getItem() {
        return item;
    }

    public String getType() {
        return type;
    }
}
