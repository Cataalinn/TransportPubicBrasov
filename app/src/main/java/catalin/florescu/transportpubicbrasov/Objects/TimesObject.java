package catalin.florescu.transportpubicbrasov.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public class TimesObject {

    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("alias")
    public String alias;

    @JsonProperty("short_url")
    public String shortURL;

    @JsonProperty("routes")
    public RoutesObject[] routes;
}
