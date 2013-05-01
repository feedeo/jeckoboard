package com.feedeo.geckoboard.web.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.feedeo.geckoboard.model.widget.GeckoboardWidget;

public class GeckoboardPushRequest {
    @JsonProperty(value = "api_key")
    private String apiKey;

    @JsonProperty(value = "data")
    private GeckoboardWidget widget;

    public GeckoboardPushRequest(String apiKey, GeckoboardWidget widget) {
        this.apiKey = apiKey;
        this.widget = widget;
    }

    public GeckoboardWidget getWidget() {
        return widget;
    }

    public void setWidget(GeckoboardWidget widget) {
        this.widget = widget;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeckoboardPushRequest)) return false;

        GeckoboardPushRequest that = (GeckoboardPushRequest) o;

        if (apiKey != null ? !apiKey.equals(that.apiKey) : that.apiKey != null) return false;
        if (widget != null ? !widget.equals(that.widget) : that.widget != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = apiKey != null ? apiKey.hashCode() : 0;
        result = 31 * result + (widget != null ? widget.hashCode() : 0);
        return result;
    }
}
