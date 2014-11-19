package com.feedeo.geckoboard.model.widget;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.feedeo.geckoboard.constant.Orientation;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class BulletGraphWidget extends GeckoboardWidget {
    private Orientation orientation;
    private Item item;

    public BulletGraphWidget(String orientation, String label, String sublabel, List<String> axisPoints, Number redStart, Number redEnd, Number amberStart, Number amberEnd, Number greenStart, Number greenEnd, String currentStart, String currentEnd, String projectedStart, String projectedEnd, String comparative) {
        this.orientation = Orientation.valueOf(orientation);
        item = new Item();
        item.setLabel(label);
        item.setSublabel(sublabel);

        item.setAxis(new Item.Axis(axisPoints));

        item.setRange(new Item.Range(redStart, redEnd, amberStart, amberEnd, greenStart, greenEnd));

        item.setMeasure(new Item.Measure(currentStart, currentEnd, projectedStart, projectedEnd));

        item.setComparative(new Item.Comparative(comparative));
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static class Item {
        private String label;
        private String sublabel;
        private Axis axis;
        private Range range;
        private Measure measure;
        private Comparative comparative;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getSublabel() {
            return sublabel;
        }

        public void setSublabel(String sublabel) {
            this.sublabel = sublabel;
        }

        public Axis getAxis() {
            return axis;
        }

        public void setAxis(Axis axis) {
            this.axis = axis;
        }

        public Range getRange() {
            return range;
        }

        public void setRange(Range range) {
            this.range = range;
        }

        public Measure getMeasure() {
            return measure;
        }

        public void setMeasure(Measure measure) {
            this.measure = measure;
        }

        public Comparative getComparative() {
            return comparative;
        }

        public void setComparative(Comparative comparative) {
            this.comparative = comparative;
        }

        @JsonInclude(NON_NULL)
        public static class Axis {
            private List<String> point;

            public Axis(List<String> point) {
                this.point = point;
            }

            public List<String> getPoint() {
                return point;
            }

            public void setPoint(List<String> point) {
                this.point = point;
            }
        }

        @JsonInclude(NON_NULL)
        public static class Range {
            private RangeItem red;
            private RangeItem amber;
            private RangeItem green;

            public Range(Number redStart, Number redEnd, Number amberStart, Number amberEnd, Number greenStart, Number greenEnd) {
                red = new RangeItem(redStart, redEnd);
                amber = new RangeItem(amberStart, amberEnd);
                green = new RangeItem(greenStart, greenEnd);
            }

            public RangeItem getRed() {
                return red;
            }

            public void setRed(RangeItem red) {
                this.red = red;
            }

            public RangeItem getAmber() {
                return amber;
            }

            public void setAmber(RangeItem amber) {
                this.amber = amber;
            }

            public RangeItem getGreen() {
                return green;
            }

            public void setGreen(RangeItem green) {
                this.green = green;
            }

            public class RangeItem {
                private Number start;
                private Number end;

                public RangeItem(Number start, Number end) {
                    this.start = start;
                    this.end = end;
                }

                public Number getStart() {
                    return start;
                }

                public void setStart(Number start) {
                    this.start = start;
                }

                public Number getEnd() {
                    return end;
                }

                public void setEnd(Number end) {
                    this.end = end;
                }
            }
        }

        @JsonInclude(NON_NULL)
        public static class Measure {
            private MeasureItem current;
            private MeasureItem projected;

            public Measure(String currentStart, String currentEnd, String projectedStart, String projectedEnd) {
                current = new MeasureItem(currentStart, currentEnd);
                projected = new MeasureItem(projectedStart, projectedEnd);
            }

            public MeasureItem getCurrent() {
                return current;
            }

            public void setCurrent(MeasureItem current) {
                this.current = current;
            }

            public MeasureItem getProjected() {
                return projected;
            }

            public void setProjected(MeasureItem projected) {
                this.projected = projected;
            }

            public class MeasureItem {
                private String start;
                private String end;

                public MeasureItem(String start, String end) {
                    this.start = start;
                    this.end = end;
                }

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getEnd() {
                    return end;
                }

                public void setEnd(String end) {
                    this.end = end;
                }
            }
        }

        @JsonInclude(NON_NULL)
        public static class Comparative {
            private String point;

            public Comparative(String comparative) {
                point = comparative;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }
        }
    }
}
