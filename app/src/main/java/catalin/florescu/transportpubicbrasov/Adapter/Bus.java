package catalin.florescu.transportpubicbrasov.Adapter;

import catalin.florescu.transportpubicbrasov.Helpers.TimeUtils;

/**
 * Created by Florescu George Cătălin on 26.09.2015.
 */
public class Bus {

    public static final String BUS = "bus", MIDI = "midi", TROLLEY = "trolley";
    public static final int GOING = 1, RETURN = 2;

    private String busType, busLine, busStation, currentHourBus, nextHourBus, endRouteStation;
    private TimeUtils countDownTime;
    private int direction, hour;

    public Bus(String busType, String busLine, String busStation, TimeUtils countDownTime, String currentHourBus, String nextHourBus, int direction, String endRouteStation){
        this.busType = busType;
        this.busLine = busLine;
        this.busStation = busStation;
        this.countDownTime = countDownTime;
        this.currentHourBus = currentHourBus;
        this.nextHourBus = nextHourBus;
        this.direction = direction;
        this.endRouteStation = endRouteStation;
    }

    public Bus(){

    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getBusStation() {
        return busStation;
    }

    public void setBusStation(String busStation) {
        this.busStation = busStation;
    }

    public String getCurrentHourBus() {
        return currentHourBus;
    }

    public void setCurrentHourBus(String currentHourBus) {
        this.currentHourBus = currentHourBus;
    }

    public String getNextHourBus() {
        return nextHourBus;
    }

    public void setNextHourBus(String nextHourBus) {
        this.nextHourBus = nextHourBus;
    }

    public TimeUtils getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(TimeUtils countDownTime) {
        this.countDownTime = countDownTime;
    }

    public String getEndRouteStation() {
        return endRouteStation;
    }

    public void setEndRouteStation(String endRouteStation) {
        this.endRouteStation = endRouteStation;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
