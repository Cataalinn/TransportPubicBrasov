package catalin.florescu.transportpubicbrasov.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public class RoutesObject {

    @JsonProperty("station_id")
    public String stationId;

    @JsonProperty("number")
    public String busNumber;

    @JsonProperty("day_hour_minutes")
    public String[] currentHourMinutes;

    @JsonProperty("day_next_hour_minutes")
    public String[] nextHourMinutes;

    @JsonProperty("time_remaining")
    public String timeRemaining;

    @JsonProperty("dir_ind")
    public Integer directionIndicator;

    @JsonProperty("route_end_station")
    public String routeEndStation;
}
