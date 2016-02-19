package catalin.florescu.transportpubicbrasov.Helpers;

import java.util.concurrent.TimeUnit;

/**
 * Created by Florescu George Cătălin on 26.09.2015.
 */
public class TimeUtils {

    private int hour, minute;

    public TimeUtils(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public long getTime(){
        return TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute);
    }
}
