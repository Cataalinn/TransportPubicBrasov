package catalin.florescu.transportpubicbrasov.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public class StationCardData {

    @JsonProperty("station_card_data")
    public TimesObject timesObject;

    @JsonProperty("curr_hour")
    public Integer currentHour;

    @JsonProperty("curr_min")
    public Integer currentMinute;

    @JsonProperty("curr_week_day")
    public String currentWeekDay;

    @JsonProperty("curr_day_of_week")
    public Integer getCurrentDayOfWeek;

}
