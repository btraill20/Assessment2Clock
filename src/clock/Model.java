package clock;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.GregorianCalendar;
import java.util.Timer;

/*
*model represents an object and can carry data, it can also have logic to update controller if data changes.
*param <T>
*/
public class Model<T> extends Observable implements PriorityQueue<T> {
    
    PriorityQueue<Alarms> q;
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    int hourtime;
    int minutetime;
    int secondtime;
    
    private Nodes<T> top;
    
    public Model(int size) {
        update();
        top = null;
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
//        String am_pm;
//        if(date.get(Calendar.AM_PM)==0)
//        am_pm="AM";
//        else
//        am_pm="PM";
    }
    
    //logic for playing a alarm which is held in controller once the set alarm has been reached
    public void playAlarm(){
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        second = date.get(Calendar.SECOND);
        Date alarmTime = date.getTime();
        Timer timer = new Timer();
        //issue with linking this timer to the controller class due to static errors.
        //timer.schedule(Controller.alarm(),alarmTime);
    }
        
    public int getHour(){
        return hourtime;
    }
    
    public void setHour(int hour){
        this.hourtime = hour;
    }
            
    public int getMinute(){
        return minutetime;
    }
    
    public void setMinute(int minute){
        this.minutetime = minute;
    }
    
    public int getSecond(){
        return secondtime;
    }

    public void setSecond(int second){
        this.secondtime = second;
    }
    
//    public void removeNode(){
//        Nodes<T> previous = top;
//        Nodes<T> current = top.next;
//        Nodes<T> highestPriority = top;
//        while(current != null)
//        {
//            if (current.index > previous.index)
//            {
//                top = previous;
//                highestPriority = current;
//            }
//
//            previous = current;
//            current = current.next;
//        }
//    }
    
   @Override
    public int size()
    {
        Nodes<T> node = top;
        int sizeresult = 0;
        while(node != null)
        {
            sizeresult = sizeresult + 1;
            node = node.getNext();
        }
        return sizeresult;
    }
    
    @Override
    public void add(int hour,int minute,int second, int priority) throws QueueOverflowException {
        top = new Nodes<>(hour,minute,second,top); 
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        top = top.getNext();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public String toString() {
        String result = "[";
                for(Nodes<T> node = top; node != null; node = node.getNext())
                {
                    if(node != top)
                    {
                    result += ", [";
                    }
                    result += node.getHour();
                    result += ":";
                    result += node.getMinute();
                    result += ":";
                    result += node.getSecond();
                    result += "]";
                }
        return result;
    }
}
