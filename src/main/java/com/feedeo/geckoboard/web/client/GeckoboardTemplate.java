package com.feedeo.geckoboard.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedeo.geckoboard.exception.UnableToPushToWidgetException;
import com.feedeo.geckoboard.model.widget.*;
import com.feedeo.geckoboard.web.message.GeckoboardPushRequest;
import com.feedeo.geckoboard.web.message.GeckoboardResponse;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class GeckoboardTemplate {
    private static String apiKey;
    private static String pushEndpoint = "https://push.geckoboard.com/v1/send/";
    private RestOperations restOperations;
    private ObjectMapper mapper;

    public GeckoboardTemplate() {
        mapper = new ObjectMapper();
    }

    private void pushToWidget(String widgetKey, GeckoboardWidget geckoboardWidget) throws UnableToPushToWidgetException {
        GeckoboardPushRequest pushRequest = new GeckoboardPushRequest(apiKey, geckoboardWidget);

        try {
            GeckoboardResponse response = restOperations.postForObject(
                    pushEndpoint + widgetKey,
                    pushRequest,
                    GeckoboardResponse.class);

            if (!response.isSuccess()) {
                throw new UnableToPushToWidgetException(response.getError());
            }
        } catch (RestClientException e) {
            throw new UnableToPushToWidgetException(e.getMessage(), e);
        }
    }

    /* Bullet Graph Widget */

    public void pushToBulletGraphWidget(String widgetKey,
                                        String orientation,
                                        String label, String sublabel, List<String> axisPoints,
                                        Number redStart, Number redEnd, Number amberStart, Number amberEnd, Number greenStart, Number greenEnd) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new BulletGraphWidget(orientation, label, sublabel, axisPoints, redStart, redEnd, amberStart, amberEnd, greenStart, greenEnd, null, null, null, null, null);
        pushToWidget(widgetKey, widget);
    }

    public void pushToBulletGraphWidget(String widgetKey,
                                        String orientation,
                                        String label, String sublabel, List<String> axisPoints,
                                        Number redStart, Number redEnd, Number amberStart, Number amberEnd, Number greenStart, Number greenEnd,
                                        String currentStart, String currentEnd, String projectedStart, String projectedEnd,
                                        String comparative) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new BulletGraphWidget(orientation, label, sublabel, axisPoints, redStart, redEnd, amberStart, amberEnd, greenStart, greenEnd, currentStart, currentEnd, projectedStart, projectedEnd, comparative);
        pushToWidget(widgetKey, widget);
    }

    /* Number and Secondary Stat Widget */

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                        Number number, Number secondaryStat) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number, secondaryStat);
        pushToWidget(widgetKey, widget);
    }

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                   Number number, Number secondaryStat, boolean reverseColour) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number, secondaryStat, reverseColour);
        pushToWidget(widgetKey, widget);
    }

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                   Number number, String text, Number secondaryStat, String secondaryText) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number, text, secondaryStat, secondaryText);
        pushToWidget(widgetKey, widget);
    }

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                   Number number, String text, Number secondaryStat, String secondaryText, boolean reverseColour) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number, text, secondaryStat, secondaryText, reverseColour);
        pushToWidget(widgetKey, widget);
    }

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                   Number number) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number);
        pushToWidget(widgetKey, widget);
    }

    public void pushToNumberAndSecondaryStatWidget(String widgetKey,
                                                   Number number, String text) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new NumberAndSecondaryStatWidget(number, text);
        pushToWidget(widgetKey, widget);
    }

    /* RAG Numbers Only Widget */

    public void pushToRagNumbersOnlyWidget(String widgetKey,
                                           Number number1, String description1,
                                           Number number2, String description2,
                                           Number number3, String description3) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new RagNumbersOnlyWidget(number1, description1, number2, description2, number3, description3);
        pushToWidget(widgetKey, widget);
    }

    /* Line Chart Widget */

    public void pushToLineChartWidget(String widgetKey, List<Number> values, List<String> axysx, List<String> axysy) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new LineChartWidget(values, axysx, axysy);
        pushToWidget(widgetKey, widget);
    }

    /* Geck-O-Meter Widget */

    public void pushToGeckOMeterWidget(String widgetKey,
                                       Number currentValue,
                                       Number minValue, String minDescription,
                                       Number maxValue, String maxDescription) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new GeckOMeterWidget(currentValue, minValue, minDescription, maxValue, maxDescription);
        pushToWidget(widgetKey, widget);
    }

     /* Leaderboard Widget */

    public void pushToLeaderBoardWidget(String widgetKey, String[] labels, Long[] values, Long[] previousRanks) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new LeaderboardWidget(labels, values, previousRanks);
        pushToWidget(widgetKey, widget);
    }

    public void pushToLeaderBoardWidget(String widgetKey, String[] labels, Long[] values) throws UnableToPushToWidgetException {
        GeckoboardWidget widget = new LeaderboardWidget(labels, values);
        pushToWidget(widgetKey, widget);
    }

    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
