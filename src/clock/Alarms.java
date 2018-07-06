package clock;

public class Alarms {
    protected int alarm;

    public Alarms(int alarm) {
        this.alarm = alarm;
    }

    public int getAlarm() {
        return alarm;
    }

    public int toOutput() {
        return getAlarm();
    }
}
