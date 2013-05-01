package com.feedeo.geckoboard.model.widget;

import com.feedeo.geckoboard.model.ValueWithText;

import java.util.ArrayList;
import java.util.List;

public class RagNumbersOnlyWidget extends GeckoboardWidget {
    private List<ValueWithText> item;

    public List<ValueWithText> getItem() {
        return item;
    }

    public RagNumbersOnlyWidget(Number number1, String description1,
                                Number number2, String description2,
                                Number number3, String description3) {
        this.item = new ArrayList<ValueWithText>(3);
        this.item.add(new ValueWithText(number1, description1));
        this.item.add(new ValueWithText(number2, description2));
        this.item.add(new ValueWithText(number3, description3));
    }
}
