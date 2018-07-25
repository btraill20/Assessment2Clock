package clock;

//@param <T>
public class Alarms<T> {
    
    protected int hour;
    protected int minute;
    protected int second;


    public Alarms(int hour, int minute, int second) {

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public int getHour(){
    return hour;
    }
    
    public int getMinute(){
    return minute;
    }
    
    public int getSecond(){
    return second;
    }
    
}

