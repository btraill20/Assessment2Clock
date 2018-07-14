package clock;

//@param <T>
public class UnsortedListqueue <T>  implements PriorityQueue<T>{
    
   private Nodes<T> top;

   //function used to determine the size of the list
    private int size()
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
    
    public UnsortedListqueue(int size)
    {
        top = null;

    }


   @Override
    public void add(T item, int priority) throws QueueOverflowException {

        // if first node
        if (this.top == null) {
            Nodes node = new Nodes(item,top);
            this.top = node;
            this.top.next = null;
        } else {
            Nodes current = this.top;
            while (current.next != null) {
                current = current.next;
            }
 
            Nodes node = new Nodes(item,top);
            current.next = node;
            current.next.next = null;
        }
 
        
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return top.getItem();
        }
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
        String result = "Sorted Linked list size =" + size();
        result += ", contents = [";
                for(Nodes<T> node = top; node != null; node = node.getNext())
                {
                    if(node != top)
                    {
                    result += ", [";
                    }
                    result += node.getItem();
                    result += "]";
                }
        return result;
    }
    
    
}