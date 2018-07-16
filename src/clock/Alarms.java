package clock;

public class Alarms {
    protected String Alarm;
    protected int hour;
    protected int minute;
    protected int second;

    public Alarms(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String getAlarm() {
        StringBuilder sb= new StringBuilder();
        sb.append(hour).append(":").append(+minute).append(":").append(+second);
        Alarm = sb.toString();
        return Alarm;
    }
    
    public String toOutput() {
        return getAlarm();
    }
}

