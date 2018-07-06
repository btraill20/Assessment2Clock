package clock;

 // @param <T>
public class Nodes<T>
{
    //this class is for setting up nodes, and their next node.
    private final T item;
    public Nodes<T> next;
    
    public Nodes(T item, Nodes<T> next)
    {
        this.item = item;
        this.next = next;
    }
    
    public T getItem()
    {
        return item;
    }
    
    public Nodes<T> getNext()
    {
        return next;
    }
}