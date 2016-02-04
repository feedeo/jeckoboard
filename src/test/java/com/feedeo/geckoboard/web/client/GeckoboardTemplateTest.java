package com.feedeo.geckoboard.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedeo.geckoboard.exception.UnableToPushToWidgetException;
import com.feedeo.geckoboard.web.message.GeckoboardPushRequest;
import com.feedeo.geckoboard.web.message.GeckoboardResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeckoboardTemplateTest {
    public static final String jsonPushRequestBulletGraphWidget = "{\"api_key\":\"\",\"data\":{\"orientation\":\"horizontal\",\"item\":{\"label\":\"Revenue 2014 YTD\",\"sublabel\":\"(U.S. $ in thousands)\",\"axis\":{\"point\":[\"0\",\"200\",\"400\",\"600\",\"800\",\"1000\"]},\"range\":{\"red\":{\"start\":0,\"end\":400},\"amber\":{\"start\":401,\"end\":700},\"green\":{\"start\":701,\"end\":1000}},\"measure\":{\"current\":{\"start\":\"0\",\"end\":\"500\"},\"projected\":{\"start\":\"100\",\"end\":\"900\"}},\"comparative\":{\"point\":\"600\"}}}}";
    public static final String jsonPushRequestToNumberAndSecondaryWidget = "{\"api_key\":\"\",\"data\":{\"item\":[{\"text\":\"\",\"value\":1},{\"text\":\"\",\"value\":2}]}}";
    public static final String jsonPushRequestToNumberRagNumbersOnlyWidget = "{\"api_key\":\"\",\"data\":{\"item\":[{\"text\":\"\",\"value\":10},{\"text\":\"\",\"value\":20},{\"text\":\"\",\"value\":1}]}}";
    public static final String jsonPushRequestToGeckOMeterWidget = "{\"api_key\":\"\",\"data\":{\"item\":10,\"max\":{\"text\":\"\",\"value\":20},\"min\":{\"text\":\"\",\"value\":1}}}";
    public static final String jsonPushRequestToLineChartWidget = "{\"api_key\":\"\",\"data\":{\"item\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"settings\":{\"axisx\":[\"00:00\",\"06:00\",\"12:00\",\"18:00\",\"00:00\"],\"axisy\":[\"Min\",\"Max\"]}}}";
    public static final String jsonPushRequestToLeaderboardWidget = "{\"api_key\":\"\",\"data\":{\"items\":[{\"label\":\"Peter\",\"value\":234,\"previous_rank\":2},{\"label\":\"Patrick\",\"value\":232,\"previous_rank\":1},{\"label\":\"Jon\",\"value\":230}]}}";

    @Mock
    private RestOperations restOperations;
    private GeckoboardTemplate target;
    private String apiKey, widgetKey;

    @Before
    public void setUp() {
        apiKey = "";
        widgetKey = "";

        target = new GeckoboardTemplate();
        target.setApiKey(apiKey);
        target.setRestOperations(restOperations);
    }

    @Test
    public void shouldPushBulletGraphWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString((GeckoboardPushRequest) args[1]);
                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestBulletGraphWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        target.pushToBulletGraphWidget(widgetKey,
                "horizontal",
                "Revenue 2014 YTD",
                "(U.S. $ in thousands)",
                asList("0", "200", "400", "600", "800", "1000"),
                0, 400,
                401, 700,
                701, 1000,
                "0", "500",
                "100", "900",
                "600");
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test
    public void shouldPushStatNumberToNumberAndSecondaryWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString((GeckoboardPushRequest)args[1]);
                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestToNumberAndSecondaryWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        target.pushToNumberAndSecondaryStatWidget(widgetKey, new Integer(1), new Integer(2));
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test
    public void shouldPushStatNumberRagNumbersOnlyWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString((GeckoboardPushRequest)args[1]);

                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestToNumberRagNumbersOnlyWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        target.pushToRagNumbersOnlyWidget(widgetKey, new Integer(10), "", new Integer(20), "", new Integer(1), "");
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test
    public void shouldPushStatToGeckOMeterWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString((GeckoboardPushRequest)args[1]);

                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestToGeckOMeterWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        target.pushToGeckOMeterWidget(widgetKey, new Integer(10), new Integer(20), "", new Integer(1), "");
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test
    public void shouldPushStatToLineChartWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString((GeckoboardPushRequest)args[1]);

                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestToLineChartWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        List<Number> values = new LinkedList<Number>();

        for (int i = 0; i < 10; i++)
            values.add(i);

        List<String> axysx = new LinkedList<String>();
        axysx.add("00:00");
        axysx.add("06:00");
        axysx.add("12:00");
        axysx.add("18:00");
        axysx.add("00:00");

        List<String> axysy = new LinkedList<String>();
        axysy.add("Min");
        axysy.add("Max");

        target.pushToLineChartWidget(widgetKey, values, axysx, axysy);
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test
    public void shouldPushLeaderboardWidget() throws UnableToPushToWidgetException {
        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws IOException {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                ObjectMapper mapper = new ObjectMapper();
                String jsonPushRequest = mapper.writer().writeValueAsString(args[1]);

                GeckoboardResponse response = new GeckoboardResponse();
                if (jsonPushRequest.equals(jsonPushRequestToLeaderboardWidget))
                    response.setSuccess(true);
                else {
                    response.setSuccess(false);
                    response.setError("");
                }

                return response;
            }
        });

        String[] labels = new String[]{
                "Peter",
                "Patrick",
                "Jon"
        };

        Long[] values = new Long[] {
                234L,
                232L,
                230L
        };

        Long[] previousRanks = new Long[] {
                2L,
                1L
        };

        target.pushToLeaderBoardWidget(widgetKey, labels, values, previousRanks);
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }

    @Test(expected = UnableToPushToWidgetException.class)
    public void shouldThrowUnableToPushException() throws UnableToPushToWidgetException {
        GeckoboardResponse response = new GeckoboardResponse();
        response.setSuccess(false);
        response.setError("");

        when(restOperations.postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class))).thenReturn(response);

        target.pushToGeckOMeterWidget(widgetKey, new Integer(10), new Integer(1), "", new Integer(15), "");
        verify(restOperations).postForObject(any(String.class), any(GeckoboardPushRequest.class), eq(GeckoboardResponse.class));
    }
}
