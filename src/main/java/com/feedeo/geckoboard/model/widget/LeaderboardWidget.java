package com.feedeo.geckoboard.model.widget;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class LeaderboardWidget extends GeckoboardWidget {
    private List<Item> items;

    public LeaderboardWidget() {
        items = new ArrayList<Item>();
    }

    public LeaderboardWidget(String[] labels, Long[] values) {
        items = new ArrayList<Item>();

        populateItems(labels, values, null);
    }

    public LeaderboardWidget(String[] labels, Long[] values, Long[] previousRanks) {
        items = new ArrayList<Item>();

        populateItems(labels, values, previousRanks);
    }

    private void populateItems(String[] labels, Long[] values, Long[] previousRanks) {
        if (labels != null && values != null && labels.length == values.length) {
            int l = labels.length;

            for (int i = 0; i < l; i++) {
                if (previousRanks != null && i < previousRanks.length) {
                    items.add(new Item(labels[i], values[i], previousRanks[i]));
                } else {
                    items.add(new Item(labels[i], values[i]));
                }
            }
        }
    }

    @JsonProperty("items")
    public List<Item> getItem() {
        return items;
    }

    public void setItem(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private String label;
        private Long value;
        private Long previousRank;

        public Item(String label, Long value) {
            this.label = label;
            this.value = value;
        }

        public Item(String label, Long value, Long previousRank) {
            this.label = label;
            this.value = value;
            this.previousRank = previousRank;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        @JsonInclude(NON_NULL)
        @JsonProperty("previous_rank")
        public Long getPreviousRank() {
            return previousRank;
        }

        public void setPreviousRank(Long previousRank) {
            this.previousRank = previousRank;
        }
    }
}
