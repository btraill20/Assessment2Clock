package clock;

//@param <T>

public interface PriorityQueue<T> {

    /**
     * @param item
     * @param priority
     * @throws QueueOverflowException
     */
    public void add(T item, int priority) throws QueueOverflowException;


    //@return The item with the highest priority
    //@throws QueueUnderflowException

    public T head() throws QueueUnderflowException;

    //@throws QueueUnderflowException

    public void remove() throws QueueUnderflowException;

    //@return True if there are no items stored, otherwise False

    public boolean isEmpty();

    @Override
    public String toString();
}
