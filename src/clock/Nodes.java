package clock;

 // @param <T>
public class Nodes<T>
{
    //this class is for setting up nodes, and their next node.
    
    protected int hour;
    protected int minute;
    protected int second;
    public Nodes<T> next;
    
    public Nodes(int hour,int minute,int second, Nodes<T> next)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.next = next;
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
    
    public Nodes<T> getNext()
    {
        return next;
    }
}