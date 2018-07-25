package clock;

//@param <T>

public interface PriorityQueue<T> {

    /**
     * @param hour
     * @param minute
     * @param second
     * @param priority
     * @throws QueueOverflowException
     */
    public void add(int hour,int minute,int second, int priority) throws QueueOverflowException;


    public void remove() throws QueueUnderflowException;

    //@return True if there are no items stored, otherwise False

    public boolean isEmpty();

    @Override
    public String toString();
    
    public int size();
}
