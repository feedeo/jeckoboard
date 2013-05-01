package com.feedeo.geckoboard.example;

import com.feedeo.geckoboard.exception.UnableToPushToWidgetException;
import com.feedeo.geckoboard.web.client.GeckoboardTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GeckoboardClientExample {
    public static String apiKey = "";
    public static String geckoOMeterWidgetKey = "";
    public static String lineChartWidgetKey = "";

    public static void main(String[] args) {
        if (apiKey.isEmpty() || geckoOMeterWidgetKey.isEmpty() || lineChartWidgetKey.isEmpty()) {
            System.out.println("Please add API and widget keys to the code example");
            return;
        }

        GeckoboardTemplate template = new GeckoboardTemplate();
        template.setApiKey(apiKey);

        RestTemplate restOperations = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();

        MediaType textHtmlMediaType = new MediaType("text", "html");
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(textHtmlMediaType);

        jsonMessageConverter.setSupportedMediaTypes(mediaTypes);
        messageConverters.add(jsonMessageConverter);

        restOperations.setMessageConverters(messageConverters);

        template.setRestOperations(restOperations);

        try {
            template.pushToGeckOMeterWidget(geckoOMeterWidgetKey, 1, 20, "", 0, "");
            System.out.println("Successfully pushed stats to Geck-O-Meter widget");
        } catch (UnableToPushToWidgetException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<Number> values = new LinkedList<Number>();

            for (int i = 0; i < 10; i++)
                values.add(i);

            for (int i = 9; i > 0; i--)
                values.add(i);

            List<String> axysx = new LinkedList<String>();
            axysx.add("00:00");
            axysx.add("06:00");
            axysx.add("12:00");
            axysx.add("18:00");
            axysx.add("00:00");

            List<String> axysy = new LinkedList<String>();
            axysy.add("Min");
            axysx.add("Max");

            template.pushToLineChartWidget(lineChartWidgetKey, values, axysx, axysy);
            System.out.println("Successfully pushed stats to Line Chart widget");
        } catch (UnableToPushToWidgetException e) {
            System.out.println(e.getMessage());
        }
    }
}
