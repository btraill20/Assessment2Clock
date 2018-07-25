package clock;

//@param <T>
public class Listqueue <T>  implements PriorityQueue<T>{
    
   private Nodes<T> top;

   //function used to determine the size of the list
   @Override
    public int size()
    {
        Nodes<T> node = top;
        int result = 0;
        while(node != null)
        {
            result = result + 1;
            node = node.getNext();
        }
        return result;
    }
    
    public Listqueue(int size)
    {
        top = null;
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
        String result = "size:"+size();
        result += ", contents = [";
                for(Nodes<T> node = top; node != null; node = node.getNext())
                {
                    if(node != top)
                    {
                    result += ", [";
                    }
                    result += node.getHour();
                    result += node.getMinute();
                    result += node.getSecond();
                    result += "]";
                }
        return result;
    }
    
    
}