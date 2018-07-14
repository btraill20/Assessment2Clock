package clock;

import java.util.Calendar;
import java.util.Observable;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    PriorityQueue<Alarms> q;
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    public Model() {
        update();
    }
    
    public void update() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
    }
    
    public void add(){
//    int alarm = Integer.parseInt(input.substring(input.lastIndexOf(' ')));
//    Alarms person = new Alarms(alarm);
//    int priority = Integer.parseInt(input.substring(input.lastIndexOf(' ') + 1));
//    System.out.println("Adding " + person.getAlarm() + " with priority " + priority);
//        try {
//            q.add(alarm, priority);
//        } catch (QueueOverflowException e) {
//            System.out.println("Add operation failed: " + e);
//        }
    }
}