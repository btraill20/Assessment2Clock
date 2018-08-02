package clock;

import java.util.Calendar;
import java.util.Observable;
import java.util.GregorianCalendar;


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
    }

    //was a method to pick a specific alarm to remove
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
